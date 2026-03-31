# Accessibility Testing Guide

This document outlines the accessibility testing approach for the GitHub Mobile application.

## Overview

Accessibility testing ensures that the application is usable by everyone, including people with disabilities. This includes:

- Visual impairments (screen reader compatibility)
- Motor impairments (touch target sizes, alternative input methods)
- Cognitive impairments (clear navigation, consistent UI)
- Hearing impairments (visual alternatives for audio content)

## Standards

We follow the Web Content Accessibility Guidelines (WCAG) 2.1 Level AA standards.

## Testing Tools

### Automated Testing

1. **Google Accessibility Scanner** (Android)
   - Scans UI components for accessibility issues
   - Identifies insufficient labels, touch target sizes, and color contrast problems

2. **Espresso Accessibility Testing** (Android)
   - Integrates with existing Espresso tests
   - Validates accessibility properties programmatically

3. **axe DevTools for Mobile**
   - Automated accessibility scanning
   - WCAG compliance validation

### Manual Testing

1. **TalkBack** (Android)
   - Built-in Android screen reader
   - Test all features with screen reader enabled

2. **Switch Access** (Android)
   - Test keyboard/switch navigation
   - Verify focus order and navigation flow

## Test Checklist

### Visual Accessibility
- [ ] All interactive elements have content descriptions
- [ ] Color contrast ratios meet WCAG AA standards (4.5:1 for text)
- [ ] Text is resizable up to 200% without loss of functionality
- [ ] UI is usable with high contrast mode enabled

### Motor Accessibility
- [ ] Touch targets are at least 48x48dp
- [ ] All features are accessible via keyboard/switch navigation
- [ ] No timing-dependent interactions without alternatives

### Cognitive Accessibility
- [ ] Navigation is consistent throughout the app
- [ ] Error messages are clear and actionable
- [ ] Forms have proper labels and instructions

### Screen Reader Compatibility
- [ ] All images have appropriate alt text
- [ ] Interactive elements announce their purpose
- [ ] Focus order is logical
- [ ] Dynamic content changes are announced

## Running Tests

### CI/CD Pipeline

Accessibility tests run automatically on every push and pull request via GitHub Actions:

```bash
# View workflow file
cat .github/workflows/accessibility-testing.yml
```

### Local Testing

To run accessibility tests locally:

```bash
# For Android with Espresso
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.example.AccessibilityTest

# For manual testing with TalkBack
# 1. Enable TalkBack in device settings
# 2. Navigate through the app using gestures
# 3. Verify all content is announced correctly
```

## Accessibility Best Practices

1. **Always provide content descriptions** for UI elements
   ```java
   imageView.setContentDescription("Profile picture");
   ```

2. **Use semantic markup** and proper view types
   ```java
   // Use Button instead of clickable TextView
   Button button = new Button(context);
   ```

3. **Ensure sufficient color contrast**
   - Text: 4.5:1 ratio
   - Large text (18pt+): 3:1 ratio
   - UI components: 3:1 ratio

4. **Make touch targets large enough**
   - Minimum 48x48dp for all interactive elements

5. **Provide text alternatives** for non-text content
   ```java
   // For images
   imageView.setContentDescription("Description of image");
   
   // For icon buttons
   iconButton.setContentDescription("Action name");
   ```

6. **Test with real users** who use assistive technologies

## Resources

- [Android Accessibility Guidelines](https://developer.android.com/guide/topics/ui/accessibility)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [Google Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor)

## Reporting Issues

If you discover accessibility issues:

1. Document the issue with screenshots/videos
2. Include steps to reproduce
3. Note which assistive technology was affected
4. Label the issue with `accessibility` tag
5. Prioritize based on severity (critical, high, medium, low)

## Continuous Improvement

We continuously monitor and improve accessibility:

- Regular audits with automated tools
- User testing with people who use assistive technologies
- Stay updated with latest accessibility standards
- Incorporate accessibility feedback into design process
