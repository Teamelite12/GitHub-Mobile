# GitHub-Mobile

A mobile-focused GitHub repository with accessibility tooling.

## Image Alt Attribute Checker

This repository includes a tool to automatically check all images in GitHub issues for proper alt attributes, ensuring accessibility compliance.

### Why This Matters

Alt attributes are essential for:
- **Accessibility**: Screen readers use alt text to describe images to visually impaired users
- **SEO**: Search engines use alt text to understand image content
- **Fallback**: When images fail to load, alt text is displayed instead

### Features

✅ Checks all issues (open and closed) in the repository
✅ Scans both issue bodies and comments  
✅ Detects both Markdown and HTML image formats
✅ Identifies missing, empty, or inadequate alt text
✅ Provides detailed error reports
✅ Automated via GitHub Actions

### Quick Start

Check all images in this repository:

```bash
python check_image_alt_attributes.py
```

See examples:

```bash
python examples.py
```

Run tests:

```bash
python test_checker.py
```

### Documentation

For detailed documentation, see [IMAGE_ALT_CHECKER_README.md](IMAGE_ALT_CHECKER_README.md)

### GitHub Actions Integration

This repository includes a GitHub Actions workflow that automatically checks images in issues. The workflow runs:
- Weekly on Sunday at midnight UTC
- When issues are created or edited
- When issue comments are created or edited
- On manual trigger

See [.github/workflows/check-image-alt-attributes.yml](.github/workflows/check-image-alt-attributes.yml) for details.

## Files in This Repository

- `check_image_alt_attributes.py` - Main checker script
- `IMAGE_ALT_CHECKER_README.md` - Comprehensive documentation
- `examples.py` - Example outputs and best practices
- `test_checker.py` - Test suite
- `main.java` - Java source file
- `.github/workflows/` - GitHub Actions workflows
  - `check-image-alt-attributes.yml` - Image alt checker workflow
  - `codeql.yml` - CodeQL security analysis

## Contributing

When creating issues in this repository, please ensure all images have proper alt attributes:

**Good:**
```markdown
![Screenshot of the dashboard showing navigation menu](screenshot.png)
```

**Bad:**
```markdown
![](screenshot.png)
```

For more examples and best practices, run `python examples.py`

## License

This repository and its tools are provided for accessibility compliance.
