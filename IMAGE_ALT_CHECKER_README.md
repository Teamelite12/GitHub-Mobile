# Image Alt Attribute Checker for GitHub Issues

This tool automatically checks all images in GitHub issues and comments to ensure they have proper alt attributes for accessibility compliance.

## Why Alt Attributes Matter

Alt attributes (alternative text) are crucial for:
- **Accessibility**: Screen readers use alt text to describe images to visually impaired users
- **SEO**: Search engines use alt text to understand image content
- **Fallback**: When images fail to load, alt text is displayed instead
- **Context**: Alt text provides context when viewing issues in text-only environments

## Features

✅ Checks all issues (open and closed) in a repository
✅ Scans both issue bodies and comments
✅ Detects both Markdown and HTML image formats
✅ Identifies missing, empty, or inadequate alt text
✅ Provides detailed error reports with context
✅ Suitable for CI/CD integration

## Requirements

- Python 3.6 or higher
- `requests` library

Install requirements:
```bash
pip install requests
```

## Usage

### Basic Usage

Check the current repository:
```bash
python check_image_alt_attributes.py
```

### Specify Repository

Check a specific repository:
```bash
python check_image_alt_attributes.py <owner> <repo>
```

Example:
```bash
python check_image_alt_attributes.py Teamelite12 GitHub-Mobile
```

### With GitHub Token (Recommended)

For higher API rate limits, set a GitHub token:
```bash
export GITHUB_TOKEN="your_github_token_here"
python check_image_alt_attributes.py
```

### In GitHub Actions

Set the token in your workflow:
```bash
GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
python check_image_alt_attributes.py
```

## What It Checks

### Markdown Images

Checks images in format: `![alt text](url)`

**Issues detected:**
- ❌ Missing alt text: `![](image.png)`
- ⚠️ Too short alt text: `![x](image.png)`

**Good examples:**
- ✅ `![Screenshot of the main dashboard](dashboard.png)`
- ✅ `![User profile showing avatar and bio](profile.png)`

### HTML Images

Checks images in format: `<img src="url" alt="text">`

**Issues detected:**
- ❌ Missing alt attribute: `<img src="image.png">`
- ❌ Empty alt attribute: `<img src="image.png" alt="">`
- ⚠️ Too short alt text: `<img src="image.png" alt="x">`

**Good examples:**
- ✅ `<img src="diagram.png" alt="Architecture diagram showing client-server interaction">`
- ✅ `<img src="logo.png" alt="Company logo">`

**Note:** Empty alt (`alt=""`) is acceptable for decorative images, but the attribute must be present.

## Output

The script provides:
1. **Summary**: Number of issues checked, images found, problems detected
2. **Error Details**: For each problem:
   - Issue number and location (body or comment)
   - Image URL
   - Problem description
   - Context preview
3. **Exit Code**: 
   - `0` if no errors found
   - `1` if errors found (suitable for CI/CD failures)

### Example Output

```
======================================================================
GitHub Issues Image Alt Attribute Checker
Repository: Teamelite12/GitHub-Mobile
======================================================================

Fetching issues from Teamelite12/GitHub-Mobile...
  Fetched page 1, total issues: 9

Checking 9 issues for image alt attributes...

======================================================================
RESULTS
======================================================================

Issues checked: 9
Images found: 3
Problems found: 2

======================================================================
ERRORS: 1
======================================================================

Issue #28 (issue body):
  Type: markdown
  URL: screenshot.png
  Problem: Missing alt text for markdown image
  Context: ...See the ![](screenshot.png) for details...

======================================================================
WARNINGS: 1
======================================================================

Issue #35 (comment #2):
  Type: html
  URL: diagram.png
  Alt text: 'x'
  Problem: Alt text is too short (less than 3 characters)
  Context: ...<img src="diagram.png" alt="x"> shows...

======================================================================
SUMMARY
======================================================================
❌ Errors: 1
⚠️  Warnings: 1

Please fix all errors to ensure accessibility compliance.

Guidelines for alt text:
- Alt text should describe the image content
- Be concise but descriptive (5-150 characters is typical)
- Don't include 'image of' or 'picture of' - it's implied
- For decorative images, use empty alt="" (but include the attribute)
```

## Alt Text Best Practices

### Good Alt Text Examples

- `![Login form with username and password fields](login-form.png)`
- `![Error message: 'Connection timeout'](error.png)`
- `![Project timeline showing milestones from Jan to Dec](timeline.png)`

### Poor Alt Text Examples

- `![](image.png)` ❌ Missing alt text
- `![pic](image.png)` ❌ Not descriptive
- `![Image of the dashboard](dashboard.png)` ⚠️ Don't start with "image of"
- `![Click here](button.png)` ❌ Not describing the image

### Writing Good Alt Text

1. **Be descriptive**: Describe what the image shows
2. **Be concise**: Aim for 5-150 characters
3. **Be specific**: Include relevant details
4. **Skip redundancy**: Don't include "image of" or "picture of"
5. **Consider context**: What information does the image convey?
6. **For decorative images**: Use `alt=""` (include attribute but leave empty)

## Integration with CI/CD

Add this to your GitHub Actions workflow to automatically check images in issues:

```yaml
name: Check Image Alt Attributes

on:
  issues:
    types: [opened, edited]
  issue_comment:
    types: [created, edited]

jobs:
  check-alt-attributes:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v4
      
      - name: Set up Python
        uses: actions/setup-python@v4
        with:
          python-version: '3.x'
      
      - name: Install dependencies
        run: pip install requests
      
      - name: Check image alt attributes
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: python check_image_alt_attributes.py
```

## Troubleshooting

### Rate Limiting

If you hit GitHub API rate limits:
- Use a GitHub token (increases limit from 60 to 5000 requests/hour)
- Run the script less frequently
- Consider caching results

### No Issues Found

If the script reports 0 issues:
- Verify the repository owner and name are correct
- Check if the repository has any issues
- Ensure you have permission to access the repository

### Authentication Errors

If you get 401/403 errors:
- Verify your GitHub token is valid
- Check token permissions (needs `repo` or `public_repo` scope)
- For private repositories, ensure token has appropriate access

## Contributing

To improve this tool:
1. Add support for more image formats
2. Enhance alt text quality checking
3. Add support for pull request comments
4. Implement caching to reduce API calls
5. Add JSON output format for programmatic use

## License

This tool is provided as-is for checking accessibility compliance in GitHub issues.
