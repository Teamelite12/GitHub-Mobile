# GitHub Mobile

A mobile application for GitHub with comprehensive accessibility testing.

## Features

- Repository search functionality
- Fully accessible UI following Material Design guidelines
- Comprehensive accessibility testing using Google's Accessibility Test Framework

## Accessibility

This app is built with accessibility as a core principle:

- ✅ All interactive elements meet minimum touch target sizes (48x48 dp)
- ✅ Content descriptions for screen readers (TalkBack)
- ✅ Proper text contrast ratios (WCAG 2.1 compliant)
- ✅ Logical navigation order
- ✅ Automated accessibility testing in CI/CD

For detailed information about our accessibility testing approach, see [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md).

## Building the App

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 17 or later
- Android SDK with API level 34

### Build Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/Teamelite12/GitHub-Mobile.git
   cd GitHub-Mobile
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run the app:
   ```bash
   ./gradlew installDebug
   ```

## Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Accessibility Tests
```bash
# Requires an emulator or connected device
./gradlew connectedAndroidTest
```

### Run Lint Checks
```bash
./gradlew lint
```

## Continuous Integration

The project includes GitHub Actions workflows for:

- **CodeQL Analysis**: Security scanning for Java, Kotlin, and Python code
- **Accessibility Testing**: Automated accessibility checks on every push and PR

## Project Structure

```
GitHub-Mobile/
├── app/
│   ├── build.gradle                 # App-level build configuration
│   ├── proguard-rules.pro          # ProGuard rules
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/               # Main application code
│       │   └── res/                # Resources (layouts, strings, etc.)
│       ├── androidTest/            # Instrumentation tests (accessibility)
│       └── test/                   # Unit tests
├── gradle/                         # Gradle wrapper
├── build.gradle                    # Project-level build configuration
├── settings.gradle                 # Gradle settings
├── ACCESSIBILITY_TESTING.md        # Detailed accessibility documentation
└── README.md                       # This file
```

## Contributing

When contributing to this project:

1. Ensure all new features follow accessibility guidelines
2. Add accessibility tests for new UI components
3. Run all tests before submitting a PR
4. Manually test with TalkBack enabled

## License

This project is open source and available under the MIT License.

## Resources

- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [Google Accessibility Test Framework](https://github.com/google/Accessibility-Test-Framework-for-Android)
