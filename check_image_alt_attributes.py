#!/usr/bin/env python3
"""
GitHub Issues Image Alt Attribute Checker

This script checks all images in GitHub issues and their comments to ensure
they have proper alt attributes for accessibility compliance.

Usage:
    python check_image_alt_attributes.py

Requirements:
    - Python 3.6+
    - requests library (pip install requests)
    - GITHUB_TOKEN environment variable (optional, for higher rate limits)
"""

import os
import re
import sys
import json
from typing import List, Dict, Optional
from dataclasses import dataclass
from enum import Enum

try:
    import requests
except ImportError:
    print("Error: requests library not found. Install it with: pip install requests")
    sys.exit(1)


class ImageType(Enum):
    """Type of image reference found"""
    MARKDOWN = "markdown"
    HTML = "html"
    HTML_NO_ALT = "html_no_alt"
    HTML_EMPTY_ALT = "html_empty_alt"


class Severity(Enum):
    """Severity level of the issue"""
    ERROR = "error"
    WARNING = "warning"
    INFO = "info"


@dataclass
class ImageIssue:
    """Represents an image with alt attribute information"""
    issue_number: int
    location: str  # "body" or "comment"
    image_type: ImageType
    url: str
    alt_text: Optional[str]
    severity: Severity
    message: str
    line_preview: str = ""


class GitHubIssueImageChecker:
    """Check alt attributes on images in GitHub issues"""
    
    def __init__(self, owner: str, repo: str, github_token: Optional[str] = None):
        """
        Initialize the checker
        
        Args:
            owner: GitHub repository owner
            repo: GitHub repository name
            github_token: Optional GitHub token for API authentication
        """
        self.owner = owner
        self.repo = repo
        self.github_token = github_token or os.environ.get('GITHUB_TOKEN')
        self.base_url = f"https://api.github.com/repos/{owner}/{repo}"
        self.headers = {
            'Accept': 'application/vnd.github.v3+json'
        }
        if self.github_token:
            self.headers['Authorization'] = f'token {self.github_token}'
        
        self.issues_checked = 0
        self.images_found = 0
        self.problems_found = []
    
    def fetch_all_issues(self) -> List[Dict]:
        """Fetch all issues (open and closed) from the repository"""
        all_issues = []
        page = 1
        
        print(f"Fetching issues from {self.owner}/{self.repo}...")
        
        while True:
            url = f"{self.base_url}/issues"
            params = {
                'state': 'all',
                'per_page': 100,
                'page': page
            }
            
            try:
                response = requests.get(url, headers=self.headers, params=params)
                response.raise_for_status()
                issues = response.json()
                
                if not issues:
                    break
                
                all_issues.extend(issues)
                print(f"  Fetched page {page}, total issues: {len(all_issues)}")
                page += 1
                
                # Avoid infinite loops
                if page > 100:
                    break
                    
            except requests.exceptions.RequestException as e:
                print(f"Error fetching issues: {e}")
                break
        
        return all_issues
    
    def fetch_issue_comments(self, issue_number: int) -> List[Dict]:
        """Fetch all comments for a specific issue"""
        url = f"{self.base_url}/issues/{issue_number}/comments"
        
        try:
            response = requests.get(url, headers=self.headers)
            response.raise_for_status()
            return response.json()
        except requests.exceptions.RequestException as e:
            print(f"Error fetching comments for issue #{issue_number}: {e}")
            return []
    
    def check_markdown_images(self, text: str, issue_number: int, location: str) -> List[ImageIssue]:
        """
        Check markdown format images: ![alt text](url)
        
        Returns list of ImageIssue objects for any problems found
        """
        issues = []
        
        # Pattern for markdown images: ![alt text](url)
        markdown_pattern = r'!\[([^\]]*)\]\(([^\)]+)\)'
        matches = re.finditer(markdown_pattern, text)
        
        for match in matches:
            alt_text = match.group(1).strip()
            url = match.group(2)
            self.images_found += 1
            
            # Get context around the image
            start = max(0, match.start() - 20)
            end = min(len(text), match.end() + 20)
            context = text[start:end].replace('\n', ' ')
            
            if not alt_text:
                issues.append(ImageIssue(
                    issue_number=issue_number,
                    location=location,
                    image_type=ImageType.MARKDOWN,
                    url=url,
                    alt_text=None,
                    severity=Severity.ERROR,
                    message="Missing alt text for markdown image",
                    line_preview=context
                ))
            elif len(alt_text) < 3:
                issues.append(ImageIssue(
                    issue_number=issue_number,
                    location=location,
                    image_type=ImageType.MARKDOWN,
                    url=url,
                    alt_text=alt_text,
                    severity=Severity.WARNING,
                    message="Alt text is too short (less than 3 characters)",
                    line_preview=context
                ))
        
        return issues
    
    def check_html_images(self, text: str, issue_number: int, location: str) -> List[ImageIssue]:
        """
        Check HTML format images: <img src="url" alt="text">
        
        Returns list of ImageIssue objects for any problems found
        """
        issues = []
        
        # Find all <img> tags
        img_pattern = r'<img[^>]*>'
        matches = re.finditer(img_pattern, text, re.IGNORECASE)
        
        for match in matches:
            img_tag = match.group(0)
            self.images_found += 1
            
            # Get context
            start = max(0, match.start() - 20)
            end = min(len(text), match.end() + 20)
            context = text[start:end].replace('\n', ' ')
            
            # Extract src
            src_match = re.search(r'src=["\']([^"\']+)["\']', img_tag, re.IGNORECASE)
            url = src_match.group(1) if src_match else "unknown"
            
            # Check for alt attribute
            alt_match = re.search(r'alt=["\']([^"\']*)["\']', img_tag, re.IGNORECASE)
            
            if not alt_match:
                # No alt attribute at all
                issues.append(ImageIssue(
                    issue_number=issue_number,
                    location=location,
                    image_type=ImageType.HTML_NO_ALT,
                    url=url,
                    alt_text=None,
                    severity=Severity.ERROR,
                    message="Missing alt attribute in HTML img tag",
                    line_preview=context
                ))
            else:
                alt_text = alt_match.group(1).strip()
                # Empty alt is acceptable for decorative images
                if len(alt_text) > 0 and len(alt_text) < 3:
                    issues.append(ImageIssue(
                        issue_number=issue_number,
                        location=location,
                        image_type=ImageType.HTML,
                        url=url,
                        alt_text=alt_text,
                        severity=Severity.WARNING,
                        message="Alt text is too short (less than 3 characters)",
                        line_preview=context
                    ))
        
        return issues
    
    def check_text_for_images(self, text: str, issue_number: int, location: str) -> List[ImageIssue]:
        """Check text for images (both markdown and HTML)"""
        if not text:
            return []
        
        issues = []
        issues.extend(self.check_markdown_images(text, issue_number, location))
        issues.extend(self.check_html_images(text, issue_number, location))
        return issues
    
    def check_issue(self, issue: Dict) -> None:
        """Check a single issue and its comments for image alt attributes"""
        issue_number = issue['number']
        self.issues_checked += 1
        
        # Check issue body
        body = issue.get('body', '') or ''
        issues = self.check_text_for_images(body, issue_number, "issue body")
        self.problems_found.extend(issues)
        
        # Check comments
        if issue.get('comments', 0) > 0:
            comments = self.fetch_issue_comments(issue_number)
            for idx, comment in enumerate(comments, 1):
                comment_body = comment.get('body', '') or ''
                comment_issues = self.check_text_for_images(
                    comment_body, 
                    issue_number, 
                    f"comment #{idx}"
                )
                self.problems_found.extend(comment_issues)
    
    def run(self) -> int:
        """
        Run the checker on all issues
        
        Returns:
            Number of problems found
        """
        print(f"\n{'='*70}")
        print(f"GitHub Issues Image Alt Attribute Checker")
        print(f"Repository: {self.owner}/{self.repo}")
        print(f"{'='*70}\n")
        
        # Fetch all issues
        issues = self.fetch_all_issues()
        
        if not issues:
            print("No issues found in repository.")
            return 0
        
        print(f"\nChecking {len(issues)} issues for image alt attributes...\n")
        
        # Check each issue
        for issue in issues:
            self.check_issue(issue)
        
        # Print results
        self.print_results()
        
        return len([p for p in self.problems_found if p.severity == Severity.ERROR])
    
    def print_results(self) -> None:
        """Print the results of the check"""
        print(f"\n{'='*70}")
        print("RESULTS")
        print(f"{'='*70}\n")
        
        print(f"Issues checked: {self.issues_checked}")
        print(f"Images found: {self.images_found}")
        print(f"Problems found: {len(self.problems_found)}")
        
        if not self.problems_found:
            print("\n✓ All images have proper alt attributes!")
            return
        
        # Group by severity
        errors = [p for p in self.problems_found if p.severity == Severity.ERROR]
        warnings = [p for p in self.problems_found if p.severity == Severity.WARNING]
        
        if errors:
            print(f"\n{'='*70}")
            print(f"ERRORS: {len(errors)}")
            print(f"{'='*70}\n")
            
            for issue in errors:
                print(f"Issue #{issue.issue_number} ({issue.location}):")
                print(f"  Type: {issue.image_type.value}")
                print(f"  URL: {issue.url}")
                print(f"  Problem: {issue.message}")
                print(f"  Context: ...{issue.line_preview}...")
                print()
        
        if warnings:
            print(f"\n{'='*70}")
            print(f"WARNINGS: {len(warnings)}")
            print(f"{'='*70}\n")
            
            for issue in warnings:
                print(f"Issue #{issue.issue_number} ({issue.location}):")
                print(f"  Type: {issue.image_type.value}")
                print(f"  URL: {issue.url}")
                print(f"  Alt text: '{issue.alt_text}'")
                print(f"  Problem: {issue.message}")
                print(f"  Context: ...{issue.line_preview}...")
                print()
        
        # Summary
        print(f"\n{'='*70}")
        print("SUMMARY")
        print(f"{'='*70}\n")
        print(f"❌ Errors: {len(errors)}")
        print(f"⚠️  Warnings: {len(warnings)}")
        
        if errors:
            print("\nPlease fix all errors to ensure accessibility compliance.")
            print("\nGuidelines for alt text:")
            print("- Alt text should describe the image content")
            print("- Be concise but descriptive (5-150 characters is typical)")
            print("- Don't include 'image of' or 'picture of' - it's implied")
            print("- For decorative images, use empty alt=\"\" (but include the attribute)")


def main():
    """Main entry point"""
    # Default to the current repository
    owner = os.environ.get('GITHUB_REPOSITORY_OWNER', 'Teamelite12')
    repo_full = os.environ.get('GITHUB_REPOSITORY', 'Teamelite12/GitHub-Mobile')
    repo = repo_full.split('/')[-1]
    
    # Allow override via command line
    if len(sys.argv) >= 3:
        owner = sys.argv[1]
        repo = sys.argv[2]
    
    checker = GitHubIssueImageChecker(owner, repo)
    
    try:
        error_count = checker.run()
        
        # Exit with error code if errors found
        sys.exit(1 if error_count > 0 else 0)
        
    except KeyboardInterrupt:
        print("\n\nInterrupted by user.")
        sys.exit(130)
    except Exception as e:
        print(f"\nError: {e}")
        import traceback
        traceback.print_exc()
        sys.exit(1)


if __name__ == "__main__":
    main()
