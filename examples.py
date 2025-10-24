#!/usr/bin/env python3
"""
Demonstration of the Image Alt Attribute Checker

This script shows example outputs for different scenarios.
"""

print("""
================================================================================
Image Alt Attribute Checker - Examples
================================================================================

This tool checks all images in GitHub issues for proper alt attributes.

Example 1: GOOD - Markdown image with descriptive alt text
--------------------------------------------------------------------------------
Markdown: ![Screenshot showing the user dashboard with navigation menu](dashboard.png)

✓ PASS - Alt text is descriptive and meaningful


Example 2: ERROR - Markdown image with missing alt text
--------------------------------------------------------------------------------
Markdown: ![](screenshot.png)

❌ FAIL - Missing alt text for markdown image
Fix: ![Screenshot of the login page](screenshot.png)


Example 3: WARNING - Markdown image with too short alt text
--------------------------------------------------------------------------------
Markdown: ![x](diagram.png)

⚠️  WARNING - Alt text is too short (less than 3 characters)
Fix: ![Architecture diagram showing client-server communication](diagram.png)


Example 4: GOOD - HTML image with alt attribute
--------------------------------------------------------------------------------
HTML: <img src="logo.png" alt="Company logo">

✓ PASS - Alt attribute is present and descriptive


Example 5: ERROR - HTML image without alt attribute
--------------------------------------------------------------------------------
HTML: <img src="chart.png">

❌ FAIL - Missing alt attribute in HTML img tag
Fix: <img src="chart.png" alt="Sales chart for Q4 2024">


Example 6: GOOD - HTML decorative image with empty alt
--------------------------------------------------------------------------------
HTML: <img src="divider.png" alt="">

✓ PASS - Empty alt is acceptable for decorative images


================================================================================
Best Practices for Alt Text
================================================================================

DO:
✓ Be descriptive: "Login form with username and password fields"
✓ Be specific: "Error message: Connection timeout"
✓ Be concise: Aim for 5-150 characters
✓ Consider context: What does the image convey?

DON'T:
✗ Leave it empty: ![](image.png)
✗ Be too vague: "pic", "image", "screenshot"
✗ Start with "image of": It's implied
✗ Use action phrases: "Click here"

For decorative images (borders, spacers, etc.):
✓ Use alt="" with the attribute present
✗ Don't omit the alt attribute entirely


================================================================================
Running the Checker
================================================================================

To check your repository:

    python check_image_alt_attributes.py

To check a specific repository:

    python check_image_alt_attributes.py owner repo

With GitHub token for higher rate limits:

    export GITHUB_TOKEN="your_token"
    python check_image_alt_attributes.py


For more information, see IMAGE_ALT_CHECKER_README.md
================================================================================
""")
