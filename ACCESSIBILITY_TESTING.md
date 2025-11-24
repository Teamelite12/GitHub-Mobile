# Accessibility Testing Guide

This document provides comprehensive information about accessibility testing in the GitHub Mobile application.

## Overview

Accessibility testing ensures that our application is usable by everyone, including people with disabilities. This includes:

- **Screen reader compatibility**: All UI elements have proper content descriptions
- **Touch target sizes**: Interactive elements meet minimum size requirements (48x48dp)
- **Color contrast**: Text and UI elements have sufficient contrast ratios
- **Text scaling**: Content remains readable when users increase text size
- **Keyboard navigation**: All functionality is accessible via keyboard/assistive devices

## Testing Framework

We use the following tools for accessibility testing:

1. **Espresso Accessibility**: Android's built-in accessibility testing framework
2. **Accessibility Test Framework (ATF)**: Google's comprehensive accessibility checker
3. **Android Lint**: Static analysis for accessibility issues

## Running Accessibility Tests

### Local Testing

To run accessibility tests locally:

```bash
# Run all accessibility tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.MainActivityAccessibilityTest

# Run with coverage
./gradlew connectedAndroidTest jacocoTestReport
```

### CI/CD Pipeline

Accessibility tests run automatically on:
- Push to main branch
- Pull request creation/update

View results in GitHub Actions under the "Accessibility Tests" workflow.

## Test Structure

### MainActivityAccessibilityTest.kt

Basic accessibility checks for the main activity:
- Verifies all elements are visible
- Checks content descriptions
- Validates basic accessibility requirements

### ComprehensiveAccessibilityTest.kt

Advanced accessibility validation:
- Uses different check presets
- Tests touch target sizes
- Validates color contrast
- Checks text scaling behavior

## Writing Accessibility Tests

### Enable Accessibility Checks

```kotlin
companion object {
    @BeforeClass
    @JvmStatic
    fun enableAccessibilityChecks() {
        AccessibilityChecks.enable()
            .setRunChecksFromRootView(true)
    }
}
```

### Test Example

```kotlin
@Test
fun testAccessibility_buttonIsAccessible() {
    onView(withId(R.id.myButton))
        .check(matches(isDisplayed()))
        .check(matches(isClickable()))
}
```

## Accessibility Checklist

When developing new features, ensure:

- [ ] All `ImageView` and `ImageButton` elements have `contentDescription`
- [ ] Interactive elements are at least 48x48dp
- [ ] Text has minimum contrast ratio of 4.5:1 (3:1 for large text)
- [ ] Form fields have proper labels
- [ ] Error messages are announced to screen readers
- [ ] Custom views implement proper accessibility delegates
- [ ] Color is not the only means of conveying information

## Common Accessibility Issues

### Missing Content Descriptions

❌ **Bad:**
```xml
<ImageButton
    android:id="@+id/closeButton"
    android:src="@drawable/ic_close" />
```

✅ **Good:**
```xml
<ImageButton
    android:id="@+id/closeButton"
    android:src="@drawable/ic_close"
    android:contentDescription="@string/close_button" />
```

### Small Touch Targets

❌ **Bad:**
```xml
<Button
    android:layout_width="32dp"
    android:layout_height="32dp" />
```

✅ **Good:**
```xml
<Button
    android:layout_width="48dp"
    android:layout_height="48dp"
    android:minWidth="48dp"
    android:minHeight="48dp" />
```

### Poor Color Contrast

❌ **Bad:**
```xml
<TextView
    android:textColor="#AAAAAA"
    android:background="#FFFFFF" />
```

✅ **Good:**
```xml
<TextView
    android:textColor="#666666"
    android:background="#FFFFFF" />
```

## Accessibility Resources

- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Google Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor)

## Continuous Improvement

We are committed to maintaining and improving accessibility:

1. All new features must pass accessibility tests
2. Regular accessibility audits are conducted
3. User feedback on accessibility is prioritized
4. Team members receive accessibility training

## Support

For questions or issues related to accessibility testing:
- Open an issue on GitHub
- Contact the development team
- Review existing accessibility documentation

---

**Remember**: Accessibility is not optional—it's essential for creating inclusive applications that everyone can use.
