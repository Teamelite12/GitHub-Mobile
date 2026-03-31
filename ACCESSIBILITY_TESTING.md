# Accessibility Testing for GitHub Mobile

This document describes the accessibility testing framework implemented for the GitHub Mobile Android application.

## Overview

Accessibility testing ensures that the app is usable by everyone, including people with disabilities. Our testing framework uses Google's Accessibility Test Framework (ATF) integrated with Espresso to automatically detect common accessibility issues.

## What We Test

### 1. Touch Target Sizes
- All interactive elements must be at least 48x48 dp (Material Design guideline)
- Tested automatically by the Accessibility Test Framework
- Explicitly verified in `MainActivityAccessibilityTest.testTouchTargets_meetMinimumSize()`

### 2. Content Descriptions
- All important views must have meaningful content descriptions for screen readers
- Text labels should be descriptive and contextual
- Tested in `MainActivityAccessibilityTest.testContentDescriptions_areSet()`

### 3. Text Contrast
- Text must have sufficient contrast ratio against backgrounds
- Follows WCAG 2.1 guidelines (4.5:1 for normal text, 3:1 for large text)
- Automatically checked by ATF

### 4. View Hierarchy
- Proper use of heading structures
- Logical navigation order
- Screen reader compatibility

## Test Structure

### Unit Tests
Located in `app/src/test/java/com/teamelite/github/mobile/`
- Basic functionality tests that don't require Android framework

### Instrumentation Tests (Accessibility Tests)
Located in `app/src/androidTest/java/com/teamelite/github/mobile/`

#### MainActivityAccessibilityTest.java
Basic accessibility tests that verify:
- Activity launches without accessibility issues
- Search field has proper content descriptions
- Search button meets accessibility requirements
- Complete interaction flows pass accessibility checks
- Touch targets meet minimum size requirements

#### AdvancedAccessibilityTest.java
Advanced accessibility validation including:
- Manual accessibility check execution
- Custom validation rules
- Specific issue detection (contrast, touch targets, labels)
- Comprehensive view hierarchy validation

## Running Tests

### Prerequisites
- Android SDK installed
- Android emulator or physical device connected
- Gradle wrapper configured

### Run Unit Tests
```bash
./gradlew test
```

### Run Accessibility Tests (Instrumentation Tests)
```bash
# Requires emulator or connected device
./gradlew connectedAndroidTest
```

### Run Lint Checks (includes accessibility lint)
```bash
./gradlew lint
```

### View Test Results
After running tests, reports are available at:
- Unit test results: `app/build/reports/tests/testDebugUnitTest/index.html`
- Instrumentation test results: `app/build/reports/androidTests/connected/index.html`
- Lint results: `app/build/reports/lint-results-debug.html`

## CI/CD Integration

The `.github/workflows/accessibility-testing.yml` workflow automatically runs:
- Unit tests on every push and pull request
- Lint checks for accessibility issues
- Uploads reports as artifacts

### Setting Up Instrumentation Tests in CI

For full instrumentation test coverage in CI/CD, you can use:

1. **GitHub Actions with Android Emulator**
   ```yaml
   - uses: reactivecircus/android-emulator-runner@v2
     with:
       api-level: 29
       script: ./gradlew connectedAndroidTest
   ```

2. **Firebase Test Lab**
   - Upload APK to Firebase Test Lab
   - Run on real devices
   - Get detailed reports

3. **AWS Device Farm**
   - Test on a wide variety of real devices
   - Comprehensive accessibility reports

## Accessibility Best Practices

### 1. Content Descriptions
Always provide content descriptions for:
- ImageViews and ImageButtons
- Interactive custom views
- Icons without text labels

```java
imageButton.setContentDescription("Search for repositories");
```

In XML:
```xml
<ImageButton
    android:contentDescription="@string/search_button_description"
    ... />
```

### 2. Minimum Touch Target Size
Ensure all interactive elements are at least 48x48 dp:

```xml
<Button
    android:minHeight="48dp"
    android:minWidth="48dp"
    ... />
```

### 3. Text Contrast
Use Material Design color palettes that ensure sufficient contrast:
- Text on light background: use dark colors
- Text on dark background: use light colors
- Avoid low-contrast color combinations

### 4. Heading Structure
Use proper heading hierarchy for screen readers:

```java
// Mark important text views as headings
textView.setAccessibilityHeading(true);
```

### 5. Live Regions
For dynamic content that should be announced:

```java
textView.setAccessibilityLiveRegion(View.ACCESSIBILITY_LIVE_REGION_POLITE);
```

## Accessibility Testing Framework (ATF)

Google's Accessibility Test Framework automatically checks for:
- Missing content descriptions
- Touch target size issues
- Text contrast problems
- Duplicate speakable text
- Clickable spans
- And many more...

### Enabling ATF in Tests

```java
@BeforeClass
public static void enableAccessibilityChecks() {
    AccessibilityChecks.enable();
}
```

This automatically runs accessibility checks on every Espresso action.

### Suppressing Specific Checks

If you need to suppress specific checks (use sparingly):

```java
AccessibilityChecks.enable()
    .setSuppressingResultMatcher(
        AccessibilityCheckResultUtils.matchesCheckNames(
            is("TouchTargetSizeCheck")
        )
    );
```

## Manual Accessibility Testing

In addition to automated tests, perform manual testing with:

1. **TalkBack Screen Reader**
   - Enable TalkBack in Android Settings > Accessibility
   - Navigate through the app using only TalkBack
   - Ensure all elements are announced properly

2. **Switch Access**
   - Test navigation using only keyboard or switch controls
   - Verify all interactive elements are reachable

3. **Font Size**
   - Test with different font sizes (Settings > Display > Font size)
   - Ensure UI adapts properly

4. **Color Contrast**
   - Test in different lighting conditions
   - Use color blindness simulators

## Resources

- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [Accessibility Test Framework](https://github.com/google/Accessibility-Test-Framework-for-Android)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)

## Troubleshooting

### Tests Fail with "No connected devices"
- Start an emulator or connect a physical device
- Run `adb devices` to verify connection

### Gradle Build Fails
- Ensure Android SDK is installed
- Check that ANDROID_HOME environment variable is set
- Sync project with Gradle files

### Accessibility Checks Fail
- Review the error message for specific issues
- Fix the accessibility issue in the code
- Re-run tests to verify fix

## Contributing

When adding new features:
1. Add content descriptions to all interactive elements
2. Ensure minimum touch target sizes (48x48 dp)
3. Write accessibility tests for new screens/features
4. Run accessibility tests before submitting PRs
5. Test manually with TalkBack enabled
