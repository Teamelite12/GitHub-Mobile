# Accessibility Testing Guide

## Overview

This project implements comprehensive accessibility testing to ensure the GitHub Mobile app is usable by everyone, including people with disabilities who rely on assistive technologies like screen readers, voice control, and switch access.

## Testing Framework

We use the following testing tools:

### 1. **Espresso Accessibility Testing**
- `espresso-accessibility`: Provides automated accessibility checks during UI tests
- Validates touch target sizes, content descriptions, and more

### 2. **Google Accessibility Test Framework (ATF)**
- `accessibility-test-framework`: Comprehensive accessibility validation
- Checks for common accessibility issues automatically

### 3. **UI Automator**
- `uiautomator`: Tests accessibility across the entire system
- Validates accessibility tree structure

## Accessibility Standards

Our tests ensure compliance with:

- **WCAG 2.1 Level AA**: Web Content Accessibility Guidelines
- **Android Accessibility Guidelines**: Platform-specific best practices
- **Material Design Accessibility**: Google's accessibility standards

## Key Accessibility Features Tested

### 1. Touch Target Sizes
- **Requirement**: Minimum 48dp x 48dp for all interactive elements
- **Why**: Ensures users with motor impairments can easily tap controls
- **Tests**: `testMinimumTouchTargetSize()`, `testTouchTargetSizes()`

### 2. Content Descriptions
- **Requirement**: All meaningful UI elements must have descriptive labels
- **Why**: Screen readers need descriptions to communicate UI to visually impaired users
- **Tests**: `testContentDescriptions()`, `testLabelConsistency()`

### 3. Text Input Accessibility
- **Requirement**: Input fields must have hints and content descriptions
- **Why**: Helps users understand what input is expected
- **Tests**: `testEditTextAccessibilityHints()`, `testTextInputAccessibility()`

### 4. Screen Reader Navigation
- **Requirement**: Logical focus order and navigable UI hierarchy
- **Why**: Screen reader users navigate sequentially through UI
- **Tests**: `testScreenReaderNavigation()`, `testImportantForAccessibility()`

### 5. Color Contrast
- **Requirement**: Minimum 4.5:1 contrast ratio for text
- **Why**: Ensures readability for users with visual impairments
- **Tests**: Automated checks via Accessibility Test Framework

## Running Accessibility Tests

### Command Line

```bash
# Run all accessibility tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.AccessibilityTest

# Run with coverage
./gradlew connectedAndroidTest jacocoTestReport
```

### Android Studio

1. Right-click on `AccessibilityTest.kt` or `AdvancedAccessibilityTest.kt`
2. Select "Run 'AccessibilityTest'"
3. View results in the Run panel

## Test Classes

### AccessibilityTest.kt
Primary accessibility test suite covering:
- Basic touch target validation
- Content description verification
- Screen reader navigation
- Search functionality accessibility
- Text input accessibility

### AdvancedAccessibilityTest.kt
Advanced accessibility tests including:
- Custom minimum size matchers
- ImportantForAccessibility validation
- Label consistency checks
- Detailed EditText accessibility

## Best Practices for Developers

### 1. Always Add Content Descriptions

```xml
<Button
    android:id="@+id/myButton"
    android:contentDescription="Descriptive text for screen readers"
    ... />
```

### 2. Ensure Minimum Touch Targets

```xml
<View
    android:minHeight="48dp"
    android:minWidth="48dp"
    ... />
```

### 3. Mark Important Elements

```xml
<TextView
    android:importantForAccessibility="yes"
    ... />
```

### 4. Provide Input Hints

```xml
<EditText
    android:hint="Enter text here"
    android:contentDescription="Descriptive label"
    ... />
```

### 5. Use Semantic HTML/XML Structure
- Use proper view hierarchies
- Group related elements
- Provide meaningful labels

## Accessibility Checklist

Before submitting code, verify:

- [ ] All interactive elements are at least 48dp x 48dp
- [ ] All images and icons have content descriptions
- [ ] All input fields have hints and descriptions
- [ ] Color is not the only means of conveying information
- [ ] Text contrast meets WCAG AA standards (4.5:1 minimum)
- [ ] All functionality is accessible via screen reader
- [ ] Focus order is logical and sequential
- [ ] Error messages are announced to screen readers
- [ ] All tests in AccessibilityTest.kt pass
- [ ] All tests in AdvancedAccessibilityTest.kt pass

## CI/CD Integration

Accessibility tests run automatically on:
- Pull request creation
- Commits to main branch
- Release builds

Builds will fail if accessibility tests fail, ensuring accessibility is never compromised.

## Resources

- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Google Accessibility Scanner](https://support.google.com/accessibility/android/answer/6376570)

## Manual Testing

In addition to automated tests, perform manual testing:

1. **Enable TalkBack**: Settings > Accessibility > TalkBack
   - Navigate the app using swipe gestures
   - Verify all elements are announced correctly

2. **Use Accessibility Scanner**: 
   - Install from Play Store
   - Scan app screens for issues

3. **Test with Large Text**:
   - Settings > Display > Font size (Largest)
   - Verify UI doesn't break

4. **Test with High Contrast**:
   - Settings > Accessibility > High contrast text
   - Verify readability

## Reporting Issues

If you find accessibility issues:
1. Create a GitHub issue with "Accessibility:" prefix
2. Include screenshots or screen recordings
3. Describe the assistive technology affected
4. Suggest a fix if possible

## Maintenance

- Review and update tests with each new feature
- Run accessibility tests before every release
- Monitor accessibility metrics and improve over time
- Stay updated with Android accessibility best practices
