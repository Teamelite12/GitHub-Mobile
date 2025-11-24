# GitHub Mobile

A mobile application for searching GitHub repositories with comprehensive accessibility testing.

## Features

- Repository search functionality
- Full accessibility support for screen readers and assistive technologies
- WCAG 2.1 Level AA compliant
- Comprehensive automated accessibility testing

## Accessibility

This app is built with accessibility as a first-class feature. See [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md) for detailed information about our accessibility testing approach.

### Accessibility Features

- ✅ Minimum 48dp touch targets for all interactive elements
- ✅ Content descriptions for all UI elements
- ✅ Screen reader compatible (TalkBack tested)
- ✅ Keyboard navigation support
- ✅ High contrast text support
- ✅ Large text support
- ✅ Proper focus order for assistive technologies

## Building the Project

### Prerequisites

- Android Studio Arctic Fox or newer
- JDK 11 or newer
- Android SDK 34
- Gradle 8.0+

### Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run lint checks
./gradlew lintDebug
```

## Testing

### Run All Tests

```bash
# Unit tests
./gradlew test

# Instrumented tests (requires emulator or device)
./gradlew connectedAndroidTest

# Accessibility tests specifically
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.AccessibilityTest
```

### Test Coverage

The project includes:
- **AccessibilityTest.kt**: Primary accessibility test suite
- **AdvancedAccessibilityTest.kt**: Advanced accessibility validation

See [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md) for complete testing documentation.

## CI/CD

Automated testing runs on every push and pull request:
- Accessibility testing workflow
- CodeQL security analysis
- Lint checks
- Build verification

## Project Structure

```
GitHub-Mobile/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/github/mobile/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   └── layout/
│   │   │   │       └── activity_main.xml
│   │   │   └── AndroidManifest.xml
│   │   └── androidTest/
│   │       └── java/com/github/mobile/
│   │           ├── AccessibilityTest.kt
│   │           └── AdvancedAccessibilityTest.kt
│   └── build.gradle
├── .github/
│   └── workflows/
│       ├── accessibility-testing.yml
│       └── codeql.yml
├── build.gradle
├── settings.gradle
├── gradle.properties
├── ACCESSIBILITY_TESTING.md
└── README.md
```

## Dependencies

### Core Libraries
- AndroidX Core KTX
- AndroidX AppCompat
- Material Components

### Testing Libraries
- JUnit 4
- AndroidX Test (JUnit, Espresso)
- Espresso Accessibility
- Google Accessibility Test Framework
- UI Automator

## Contributing

When contributing to this project:

1. Ensure all accessibility tests pass
2. Add content descriptions to new UI elements
3. Follow the accessibility checklist in ACCESSIBILITY_TESTING.md
4. Test with TalkBack enabled
5. Verify minimum touch target sizes (48dp x 48dp)

## Resources

- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)

## License

This project is for demonstration purposes.

## Support

For accessibility issues or questions, please create a GitHub issue with the "Accessibility:" prefix.
