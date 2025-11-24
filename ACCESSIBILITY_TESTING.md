# Accessibility Testing Guide

This document provides guidelines and instructions for accessibility testing in the GitHub Mobile application.

## Overview

Accessibility testing ensures that our application is usable by everyone, including people with disabilities. This includes:

- Visual impairments (screen reader users)
- Motor impairments (keyboard-only navigation)
- Hearing impairments (captions, visual indicators)
- Cognitive impairments (clear, simple interfaces)

## Running Accessibility Tests

### Prerequisites

- Android SDK installed
- Android device or emulator running API level 21 or higher

### Run all accessibility tests

```bash
./gradlew connectedAndroidTest
```

### Run specific accessibility test class

```bash
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.MainActivityAccessibilityTest
```

### Run advanced accessibility tests

```bash
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.accessibility.AdvancedAccessibilityTest
```

## Accessibility Test Framework

We use Google's Accessibility Test Framework (ATF) which automatically checks for:

- **Touch target size**: Minimum 48x48 dp for interactive elements
- **Content descriptions**: All clickable items must have descriptions
- **Color contrast**: Text must have sufficient contrast with background
- **Text size**: Text must be readable and respect system font size settings
- **Clickable spans**: Links and buttons must be properly identified

## Key Accessibility Principles

### 1. Content Descriptions

All interactive elements must have meaningful content descriptions:

```xml
<Button
    android:id="@+id/submit_button"
    android:contentDescription="Submit form"
    ... />
```

### 2. Touch Target Size

Interactive elements must be at least 48dp x 48dp:

```xml
<Button
    android:minWidth="48dp"
    android:minHeight="48dp"
    ... />
```

### 3. Focus Order

Ensure logical focus order for keyboard and screen reader navigation:

```xml
<View
    android:focusable="true"
    android:nextFocusDown="@id/next_view"
    ... />
```

### 4. Color Contrast

- Normal text: 4.5:1 contrast ratio (WCAG AA)
- Large text (18pt+): 3:1 contrast ratio (WCAG AA)

### 5. Text Scaling

Use `sp` (scale-independent pixels) for text sizes to respect user font size preferences:

```xml
<TextView
    android:textSize="16sp"
    ... />
```

## Testing with TalkBack

### Enable TalkBack on Android

1. Open Settings > Accessibility
2. Enable TalkBack
3. Navigate using swipe gestures

### Manual Testing Checklist

- [ ] All interactive elements are announced by TalkBack
- [ ] Content descriptions are meaningful and concise
- [ ] Navigation order is logical
- [ ] All actions can be performed without visual feedback
- [ ] Form inputs are properly labeled

## Common Accessibility Issues

### Missing Content Descriptions

**Problem**: Buttons or images without content descriptions

**Solution**: Add `android:contentDescription` attribute

```xml
<ImageButton
    android:contentDescription="@string/menu_description"
    ... />
```

### Small Touch Targets

**Problem**: Interactive elements smaller than 48dp x 48dp

**Solution**: Use `android:minWidth` and `android:minHeight`

```xml
<ImageView
    android:minWidth="48dp"
    android:minHeight="48dp"
    android:clickable="true"
    ... />
```

### Poor Color Contrast

**Problem**: Text that's hard to read due to low contrast

**Solution**: Use Material Design color palette or verify with contrast checker

### Decorative Images

**Problem**: Decorative images being announced by screen readers

**Solution**: Mark as not important for accessibility

```xml
<ImageView
    android:importantForAccessibility="no"
    android:contentDescription="@null"
    ... />
```

## Accessibility Testing Tools

### Automated Testing

- **Google Accessibility Test Framework**: Integrated in our test suite
- **Espresso Accessibility**: Used in our instrumentation tests
- **AccessibilityValidator**: Validates views against accessibility guidelines

### Manual Testing Tools

- **TalkBack**: Android's built-in screen reader
- **Switch Access**: For testing keyboard/switch navigation
- **Accessibility Scanner**: Google's app for scanning accessibility issues

### Third-Party Tools

- **Accessibility Insights for Android**: Detailed accessibility assessment
- **WAVE**: Web content accessibility evaluation tool (for web views)

## CI/CD Integration

Accessibility tests are automatically run in our GitHub Actions workflow:

```yaml
- name: Run Accessibility Tests
  run: ./gradlew connectedAndroidTest
```

## Resources

- [Android Accessibility Documentation](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Google Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor)

## Contributing

When adding new features:

1. Add content descriptions to all interactive elements
2. Ensure minimum touch target sizes
3. Write accessibility tests for new UI components
4. Test with TalkBack enabled
5. Run automated accessibility tests before submitting PR

## Best Practices

1. **Test early and often**: Run accessibility tests during development
2. **Use semantic markup**: Choose the right widget for the task
3. **Provide alternatives**: Captions for videos, text for images
4. **Keep it simple**: Clear, concise language and straightforward navigation
5. **Test with real users**: Include people with disabilities in user testing
