# GitHub Mobile - Accessibility Testing

This repository demonstrates accessibility testing best practices for Android mobile applications.

## Accessibility Features

### Implemented Accessibility Best Practices

1. **Content Descriptions**: All interactive UI elements have meaningful content descriptions for screen readers
2. **Touch Target Size**: All interactive elements meet the minimum 48x48dp touch target requirement
3. **Text Readability**: Text uses appropriate sizes (minimum 16sp) for readability
4. **Color Contrast**: UI follows WCAG AA guidelines with minimum 4.5:1 contrast ratio
5. **Screen Reader Support**: Proper announcements for dynamic content changes
6. **Keyboard Navigation**: UI supports alternative input methods

## Testing Framework

This project uses:
- **Google Accessibility Test Framework for Android**: Automated accessibility validation
- **Espresso**: UI testing with accessibility checks
- **JUnit**: Unit testing for accessibility utilities

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/github/mobile/
│   │   │   ├── MainActivity.java          # Main activity with accessibility features
│   │   │   └── AccessibilityUtils.java    # Accessibility helper utilities
│   │   ├── res/
│   │   │   └── layout/
│   │   │       └── activity_main.xml      # Accessible layout with proper attributes
│   │   └── AndroidManifest.xml
│   ├── androidTest/
│   │   └── java/com/github/mobile/
│   │       └── MainActivityAccessibilityTest.java  # Instrumented accessibility tests
│   └── test/
│       └── java/com/github/mobile/
│           └── AccessibilityUtilsTest.java         # Unit tests for utilities
└── build.gradle
```

## Running Tests

### Unit Tests
```bash
./gradlew test
```

### Instrumented Accessibility Tests
```bash
./gradlew connectedAndroidTest
```

### Lint Checks
```bash
./gradlew lint
```

## Accessibility Guidelines Followed

### WCAG 2.1 Level AA Compliance
- **Perceivable**: Content is presentable to users in ways they can perceive
- **Operable**: UI components are operable via multiple input methods
- **Understandable**: Information and UI operation is understandable
- **Robust**: Content works with current and future assistive technologies

### Android Accessibility Guidelines
- Minimum 48x48dp touch targets
- Content descriptions for all interactive elements
- Support for TalkBack screen reader
- Proper focus handling
- Dynamic content announcements

## Continuous Integration

The project includes GitHub Actions workflows that:
- Run accessibility tests on every push and pull request
- Generate test reports
- Perform lint checks
- Upload test artifacts

## Manual Testing Tools

### Recommended Tools
1. **TalkBack**: Android's built-in screen reader
2. **Accessibility Scanner**: Google's accessibility testing app
3. **Switch Access**: For testing alternative input methods
4. **Large Text & Display Settings**: Test with different system settings

### Manual Testing Checklist
- [ ] Navigate the app using TalkBack
- [ ] Test with large text settings (200% scale)
- [ ] Verify color contrast in different lighting conditions
- [ ] Test with Switch Access or external keyboard
- [ ] Check touch target sizes on different screen sizes
- [ ] Verify all images have meaningful descriptions
- [ ] Test dynamic content announcements

## Resources

- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [Google Accessibility Test Framework](https://github.com/google/Accessibility-Test-Framework-for-Android)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)

## Contributing

When adding new features, ensure:
1. All interactive elements have content descriptions
2. Touch targets are at least 48x48dp
3. Accessibility tests are added or updated
4. Manual testing with TalkBack is performed
5. Color contrast meets WCAG AA standards

## License

This project is provided as a demonstration of accessibility testing best practices.
