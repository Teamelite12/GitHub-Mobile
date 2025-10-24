#!/usr/bin/env python3
"""
Test the image alt attribute checker with sample data
"""

import sys
import os

# Add parent directory to path
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from check_image_alt_attributes import GitHubIssueImageChecker

# Create sample test data
sample_issues = [
    {
        'number': 1,
        'body': '''
# Issue with images

Here's a screenshot: ![Screenshot of dashboard](screenshot.png)

And another one with no alt: ![](bad-image.png)

HTML image: <img src="test.png" alt="Test image">

HTML image without alt: <img src="no-alt.png">

HTML image with empty alt: <img src="empty.png" alt="">
        ''',
        'comments': 1
    },
    {
        'number': 2,
        'body': 'No images in this issue',
        'comments': 0
    },
    {
        'number': 3,
        'body': '''
Short alt: ![x](short.png)

Good alt: ![Detailed diagram showing the architecture](diagram.png)
        ''',
        'comments': 0
    }
]

sample_comments = {
    1: [
        {
            'body': '''
            Comment with image: ![Good alt text here](comment-image.png)
            
            And a bad one: ![](comment-bad.png)
            '''
        }
    ]
}


class MockChecker(GitHubIssueImageChecker):
    """Mock checker that uses sample data instead of API"""
    
    def fetch_all_issues(self):
        """Return sample issues"""
        print(f"Using {len(sample_issues)} sample issues for testing...")
        return sample_issues
    
    def fetch_issue_comments(self, issue_number):
        """Return sample comments"""
        return sample_comments.get(issue_number, [])


def main():
    print("="*70)
    print("Testing Image Alt Attribute Checker with Sample Data")
    print("="*70)
    print()
    
    checker = MockChecker("test-owner", "test-repo")
    error_count = checker.run()
    
    print("\n" + "="*70)
    print("TEST COMPLETED")
    print("="*70)
    print(f"Exit code would be: {error_count}")
    
    # Verify we found the expected issues
    expected_errors = 3  # ![](bad-image.png), <img src="no-alt.png">, ![](comment-bad.png)
    expected_warnings = 1  # ![x](short.png)
    # Note: <img src="empty.png" alt=""> is acceptable for decorative images
    
    actual_errors = len([p for p in checker.problems_found if p.severity.value == "error"])
    actual_warnings = len([p for p in checker.problems_found if p.severity.value == "warning"])
    
    print(f"\nExpected: {expected_errors} errors, {expected_warnings} warnings")
    print(f"Actual:   {actual_errors} errors, {actual_warnings} warnings")
    
    if actual_errors == expected_errors and actual_warnings == expected_warnings:
        print("\n✓ Test PASSED - All expected issues were found!")
        return 0
    else:
        print("\n✗ Test FAILED - Issue count mismatch!")
        return 1


if __name__ == "__main__":
    sys.exit(main())
