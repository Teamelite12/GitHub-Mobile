# GitHub Mobile

A mobile application for GitHub with comprehensive accessibility support.

## Accessibility Testing

This project implements comprehensive accessibility testing to ensure the application is usable by everyone, including people with disabilities.

### Quick Start

1. **Automated Testing**: Accessibility tests run automatically on every push and pull request
   ```bash
   # Tests run via GitHub Actions
   # See .github/workflows/accessibility-testing.yml
   ```

2. **Manual Testing**: Use the provided test suite
   ```bash
   # Run accessibility tests locally
   java AccessibilityTests.java
   ```

3. **Review Guidelines**: See [ACCESSIBILITY.md](./ACCESSIBILITY.md) for detailed guidelines

### Features

- ✅ Automated accessibility scanning in CI/CD pipeline
- ✅ WCAG 2.1 Level AA compliance
- ✅ Screen reader compatibility testing
- ✅ Color contrast validation
- ✅ Touch target size verification
- ✅ Keyboard navigation support
- ✅ Comprehensive test suite

### Documentation

- [Accessibility Testing Guide](./ACCESSIBILITY.md) - Comprehensive guide to accessibility testing
- [Accessibility Configuration](./accessibility-config.yml) - Testing configuration and thresholds
- [Test Examples](./AccessibilityTests.java) - Sample accessibility test implementations

### Running Tests

#### CI/CD Pipeline
Tests run automatically on:
- Every push to main branch
- Every pull request

View results in the Actions tab of this repository.

#### Local Testing

Run the accessibility test suite:
```bash
javac AccessibilityTests.java
java AccessibilityTests
```

### Standards Compliance

This project follows:
- Web Content Accessibility Guidelines (WCAG) 2.1 Level AA
- Android Accessibility Guidelines
- Material Design Accessibility Standards

### Contributing

When contributing to this project:

1. Ensure all new features include accessibility considerations
2. Add appropriate content descriptions to UI elements
3. Maintain minimum touch target sizes (48x48dp)
4. Verify color contrast ratios
5. Test with TalkBack screen reader
6. Run accessibility tests before submitting PRs

See [ACCESSIBILITY.md](./ACCESSIBILITY.md) for detailed guidelines.

### Resources

- [Android Accessibility](https://developer.android.com/guide/topics/ui/accessibility)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)

## License

This project is licensed under the MIT License.
