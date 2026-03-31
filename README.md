# GitHub Mobile - Accessibility Testing

An Android application demonstrating comprehensive accessibility testing practices.

## Features

- Complete accessibility testing infrastructure
- Google Accessibility Test Framework integration
- Espresso-based accessibility tests
- TalkBack compatibility testing
- Touch target size validation
- Content description verification
- Color contrast checking

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
│   │   └── androidTest/
│   │       └── java/com/github/mobile/
│   │           ├── MainActivityAccessibilityTest.java
│   │           └── accessibility/
│   │               ├── AccessibilityTestHelper.java
│   │               └── AdvancedAccessibilityTest.java
│   ├── build.gradle
│   └── proguard-rules.pro
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

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 17 or later
- Android SDK with API level 34
- Gradle 8.1.0 or later

### Building the Project

```bash
# Clone the repository
git clone https://github.com/Teamelite12/GitHub-Mobile.git
cd GitHub-Mobile

# Build the project
./gradlew build
```

### Running Tests

#### Run all tests
```bash
./gradlew test
```

#### Run accessibility tests (requires emulator/device)
```bash
./gradlew connectedAndroidTest
```

#### Run specific test class
```bash
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.MainActivityAccessibilityTest
```

## Accessibility Testing

This project includes comprehensive accessibility testing to ensure the app is usable by everyone. See [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md) for detailed documentation.

### Test Coverage

- **Content Descriptions**: Verify all interactive elements have meaningful descriptions
- **Touch Target Sizes**: Ensure minimum 48dp x 48dp for clickable elements
- **Color Contrast**: Validate WCAG compliance for text visibility
- **Focus Order**: Test logical navigation for keyboard/screen readers
- **TalkBack Compatibility**: Ensure proper announcements for screen reader users

### Accessibility Test Classes

1. **MainActivityAccessibilityTest**: Core accessibility tests
   - Content description verification
   - Touch target size validation
   - Accessibility importance checks
   - Comprehensive accessibility scans

2. **AdvancedAccessibilityTest**: Advanced accessibility scenarios
   - Color contrast testing
   - Keyboard accessibility
   - Content description quality
   - Touch target overlap detection

3. **AccessibilityTestHelper**: Utility class for common accessibility checks
   - Accessibility validator creation
   - Touch target size verification
   - Content description helpers

## CI/CD

The project uses GitHub Actions for continuous integration:

- **CodeQL Analysis**: Security vulnerability scanning
- **Accessibility Testing**: Automated accessibility tests on every PR

See `.github/workflows/` for workflow configurations.

## Dependencies

### Main Dependencies
- AndroidX AppCompat
- Material Components for Android
- ConstraintLayout

### Test Dependencies
- JUnit 4
- AndroidX Test (Espresso, JUnit extensions)
- Google Accessibility Test Framework
- Espresso Accessibility

## Best Practices

This project demonstrates:

1. **Proper content descriptions** for all interactive elements
2. **Minimum touch target sizes** (48dp x 48dp)
3. **Semantic markup** using appropriate widgets
4. **Text scaling support** using `sp` units
5. **Automated accessibility testing** in CI/CD pipeline

## Contributing

When contributing:

1. Ensure all new UI components have content descriptions
2. Maintain minimum touch target sizes
3. Write accessibility tests for new features
4. Test with TalkBack enabled
5. Run accessibility tests before submitting PRs

## Resources

- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Google Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor)

## License

This project is open source and available for educational purposes.

## Contact

For questions or issues, please open an issue on GitHub.
