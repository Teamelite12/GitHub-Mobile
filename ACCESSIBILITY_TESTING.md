# Accessibility Testing Guide

## Overview

This document provides guidelines for accessibility testing in the GitHub Mobile application. We use automated accessibility testing tools to ensure our app meets WCAG (Web Content Accessibility Guidelines) standards.

## Testing Framework

We use the following tools for accessibility testing:

- **Espresso Accessibility**: Android's UI testing framework with accessibility support
- **Google Accessibility Test Framework**: Automated accessibility checks
- **Android Accessibility Scanner**: For manual accessibility audits

## Running Accessibility Tests

### Local Testing

Run all accessibility tests:
```bash
./gradlew test
./gradlew connectedAndroidTest
```

Run specific test class:
```bash
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.AccessibilityTest
```

### CI/CD Testing

Accessibility tests run automatically on:
- Every pull request
- Pushes to the main branch

Results are available in the GitHub Actions artifacts.

## Accessibility Guidelines

### 1. Touch Target Size
- All interactive elements must be at least 48dp x 48dp
- Buttons, links, and input fields should have adequate spacing

### 2. Content Descriptions
- All UI elements must have meaningful content descriptions
- Images, icons, and buttons need descriptive labels for screen readers
- Use `android:contentDescription` attribute

Example:
```xml
<Button
    android:id="@+id/login_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Login"
    android:contentDescription="Login button to authenticate user" />
```

### 3. Text Contrast
- Text must have sufficient contrast ratio (4.5:1 for normal text, 3:1 for large text)
- Avoid light text on light backgrounds or dark text on dark backgrounds

### 4. Focus Order
- Ensure logical navigation order for keyboard and screen reader users
- Use `android:nextFocusDown`, `android:nextFocusUp`, etc. if needed

### 5. Text Size and Readability
- Support dynamic text sizing
- Use sp units for text sizes
- Test with larger text settings

### 6. Color Independence
- Don't rely solely on color to convey information
- Use icons, labels, or patterns in addition to color

### 7. Form Labels and Hints
- All input fields must have labels or hints
- Error messages should be clear and helpful

## Automated Checks

Our accessibility tests automatically verify:

✅ Touch target sizes meet minimum requirements  
✅ All UI elements have content descriptions  
✅ Text contrast ratios meet WCAG standards  
✅ Clickable items are properly labeled  
✅ Focus order is logical  
✅ Text is scalable  

## Manual Testing

In addition to automated tests, perform manual testing with:

1. **TalkBack**: Android's built-in screen reader
   - Enable in Settings > Accessibility > TalkBack
   - Navigate through the app using TalkBack gestures

2. **Switch Access**: For users with mobility impairments
   - Enable in Settings > Accessibility > Switch Access

3. **Font Size**: Test with different font sizes
   - Settings > Display > Font size (set to largest)

4. **Color Filters**: Test with color blindness simulations
   - Settings > Accessibility > Color correction

## Best Practices

1. **Start Early**: Incorporate accessibility from the beginning of development
2. **Test Regularly**: Run accessibility tests with every build
3. **Use Real Devices**: Test on actual devices with accessibility features enabled
4. **Get Feedback**: Include users with disabilities in testing
5. **Document Issues**: Create tickets for accessibility bugs with clear reproduction steps

## Common Issues and Solutions

### Issue: Small Touch Targets
**Solution**: Increase `minWidth` and `minHeight` to at least 48dp

### Issue: Missing Content Description
**Solution**: Add `android:contentDescription` to all interactive and informative elements

### Issue: Poor Text Contrast
**Solution**: Use Material Design color palette with sufficient contrast ratios

### Issue: Unlabeled Form Fields
**Solution**: Add `android:hint` or associated labels with `android:labelFor`

## Resources

- [Android Accessibility Documentation](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Google Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor)

## Contributing

When adding new features:
1. Add accessibility tests for new UI components
2. Verify all interactive elements meet accessibility standards
3. Update this guide if introducing new accessibility patterns

## Support

For questions or issues related to accessibility testing:
- Open an issue in the repository
- Review existing accessibility test examples
- Consult the Android Accessibility documentation
