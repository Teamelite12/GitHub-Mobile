# GitHub Mobile

An Android mobile application for GitHub with comprehensive accessibility testing.

## Features

- Native Android application built with Kotlin
- Material Design UI components
- Comprehensive accessibility support
- Automated accessibility testing

## Accessibility

This application is built with accessibility as a core principle. We ensure:

- ✅ Screen reader compatibility (TalkBack)
- ✅ Minimum touch target sizes (48x48dp)
- ✅ WCAG 2.1 AA color contrast ratios
- ✅ Scalable text and UI elements
- ✅ Keyboard navigation support

See [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md) for detailed information about our accessibility testing approach.

## Building the Project

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 17 or later
- Android SDK with API level 34

### Build Instructions

```bash
# Clone the repository
git clone https://github.com/Teamelite12/GitHub-Mobile.git
cd GitHub-Mobile

# Build the project
./gradlew build

# Run tests
./gradlew test

# Run accessibility tests
./gradlew connectedAndroidTest
```

## Testing

### Unit Tests

```bash
./gradlew test
```

### Accessibility Tests

```bash
./gradlew connectedAndroidTest
```

### Lint Checks

```bash
./gradlew lintDebug
```

## CI/CD

The project uses GitHub Actions for continuous integration:

- **CodeQL Analysis**: Security and code quality scanning
- **Accessibility Tests**: Automated accessibility validation

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Ensure all tests pass, including accessibility tests
5. Submit a pull request

All contributions must maintain or improve accessibility standards.

## License

This project is part of the Teamelite12 organization.

## Contact

For questions or support, please open an issue on GitHub.
