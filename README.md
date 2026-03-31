# GitHub Mobile

An Android mobile application demonstrating best practices for accessibility testing.

## Features

- User-friendly login interface
- Comprehensive accessibility support
- Automated accessibility testing
- WCAG 2.1 compliance

## Accessibility

This application is built with accessibility in mind, following Android and WCAG guidelines:

- ✅ All interactive elements meet minimum touch target sizes (48dp x 48dp)
- ✅ All UI elements have proper content descriptions for screen readers
- ✅ Text meets WCAG contrast ratio requirements
- ✅ Supports dynamic text sizing
- ✅ Logical focus order for keyboard navigation
- ✅ TalkBack compatible

## Testing

### Running Tests

Run unit tests:
```bash
./gradlew test
```

Run accessibility tests (requires Android emulator or device):
```bash
./gradlew connectedAndroidTest
```

Run all checks:
```bash
./gradlew check
```

### Accessibility Testing

See [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md) for comprehensive accessibility testing guidelines.

Our automated accessibility tests verify:
- Touch target sizes
- Content descriptions
- Text contrast ratios
- Focus order
- Screen reader compatibility

## Build

Build debug APK:
```bash
./gradlew assembleDebug
```

Build release APK:
```bash
./gradlew assembleRelease
```

## Requirements

- Android SDK 21 or higher
- Java 8 or higher
- Gradle 8.2 or higher

## Project Structure

```
GitHub-Mobile/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/github/mobile/
│   │   │   │   └── MainActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   └── values/
│   │   │   │       └── strings.xml
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/
│   │   │   └── java/com/github/mobile/
│   │   │       └── AccessibilityTest.java
│   │   └── test/
│   │       └── java/com/github/mobile/
│   │           └── AccessibilityUnitTest.java
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── ACCESSIBILITY_TESTING.md
```

## CI/CD

Accessibility tests run automatically via GitHub Actions on:
- Every push to main branch
- Every pull request

See `.github/workflows/accessibility-tests.yml` for configuration.

## Contributing

When contributing, please ensure:
1. All new UI components include accessibility attributes
2. Accessibility tests are added for new features
3. All tests pass before submitting PR
4. Code follows existing style and conventions

## License

This project is created for educational purposes to demonstrate accessibility testing in Android applications.

## Resources

- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
