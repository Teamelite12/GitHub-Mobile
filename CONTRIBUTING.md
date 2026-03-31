# Contributing to GitHub Mobile

Thank you for your interest in contributing to GitHub Mobile! This guide will help you understand our accessibility testing requirements.

## Accessibility Requirements

All contributions must meet accessibility standards to ensure the app is usable by everyone.

### Before Submitting a Pull Request

1. **Add Content Descriptions**: All UI elements must have meaningful content descriptions
   ```java
   button.setContentDescription("Submit registration form");
   ```

2. **Test Accessibility**: Run the accessibility test suite
   ```bash
   ./gradlew test  # Run unit tests
   ./gradlew connectedAndroidTest  # Run instrumented accessibility tests
   ```

3. **Verify Touch Targets**: Ensure interactive elements are at least 48dp x 48dp

4. **Check Color Contrast**: Maintain at least 4.5:1 contrast ratio for normal text

### Writing Accessible Code

#### Good Examples

```java
// ✅ Good: Descriptive content description
Button submitButton = new Button(this);
submitButton.setText("Submit");
submitButton.setContentDescription("Submit registration form");

// ✅ Good: Adequate touch target size
submitButton.setMinimumWidth(dpToPx(48));
submitButton.setMinimumHeight(dpToPx(48));

// ✅ Good: Dynamic content description updates
textView.setText("Loading...");
textView.setContentDescription("Status: Loading data");
```

#### Bad Examples

```java
// ❌ Bad: Missing content description
Button button = new Button(this);
button.setText("OK");
// Missing setContentDescription()

// ❌ Bad: Vague content description
button.setContentDescription("Button");

// ❌ Bad: Too small touch target
button.setWidth(dpToPx(30));  // Less than 48dp
```

### Accessibility Test Coverage

When adding new features:

1. Add accessibility tests in `app/src/androidTest/java/com/github/mobile/`
2. Include tests for:
   - Content descriptions
   - Touch target sizes
   - Screen reader compatibility
   - Keyboard navigation (if applicable)

Example test structure:

```java
@Test
public void testNewFeatureAccessibility() {
    AccessibilityChecks.enable();
    ActivityScenario<YourActivity> scenario = ActivityScenario.launch(YourActivity.class);
    // Test interactions
    scenario.close();
}
```

## Testing Guidelines

### Running Tests Locally

```bash
# Run all unit tests
./gradlew test

# Run specific test class
./gradlew test --tests AccessibilityUnitTest

# Run instrumented tests (requires emulator/device)
./gradlew connectedAndroidTest
```

### Continuous Integration

All pull requests automatically run:
- Unit tests
- Accessibility validation tests
- CodeQL security analysis

Tests must pass before merging.

## Code Review Checklist

Before requesting review, ensure:

- [ ] All new UI elements have content descriptions
- [ ] Touch targets meet 48dp minimum
- [ ] Accessibility tests pass
- [ ] Unit tests added for new functionality
- [ ] Documentation updated if needed
- [ ] No accessibility warnings in test output

## Resources

- [Android Accessibility Guidelines](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Google Accessibility Test Framework](https://github.com/google/Accessibility-Test-Framework-for-Android)

## Questions?

If you have questions about accessibility requirements, please:
1. Check the [README.md](README.md) for examples
2. Review existing accessibility tests
3. Open an issue for clarification

## Thank You!

Your commitment to accessibility helps make GitHub Mobile usable by everyone. Thank you for contributing!
