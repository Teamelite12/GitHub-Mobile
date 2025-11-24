# Contributing to GitHub Mobile

Thank you for your interest in contributing to GitHub Mobile! This guide will help you set up your development environment and understand our accessibility testing requirements.

## Development Setup

### Prerequisites

1. **Android Studio** (Arctic Fox or later)
   - Download from [developer.android.com](https://developer.android.com/studio)
   
2. **JDK 17 or later**
   - Android Studio includes a JDK, or you can install separately
   
3. **Android SDK**
   - Install via Android Studio SDK Manager
   - Minimum SDK: API 24 (Android 7.0)
   - Target SDK: API 34 (Android 14)

### Setting Up the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/Teamelite12/GitHub-Mobile.git
   cd GitHub-Mobile
   ```

2. Open the project in Android Studio:
   - File → Open → Select the GitHub-Mobile directory

3. Sync Gradle:
   - Android Studio should automatically prompt to sync
   - Or manually: File → Sync Project with Gradle Files

4. Run the app:
   - Connect an Android device or start an emulator
   - Click Run (▶) or press Shift+F10

## Running Tests

### Unit Tests

```bash
./gradlew test
```

### Accessibility Tests (Instrumentation Tests)

**On Emulator/Device:**
```bash
./gradlew connectedAndroidTest
```

**Specific Test Class:**
```bash
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.MainActivityAccessibilityTest
```

**Generate Coverage Report:**
```bash
./gradlew connectedAndroidTest jacocoTestReport
```

### Lint Checks

```bash
./gradlew lintDebug
```

The lint report will be generated at: `app/build/reports/lint-results-debug.html`

## Accessibility Testing Requirements

**All contributions MUST pass accessibility tests.** We are committed to building an inclusive app that works for everyone.

### Before Submitting a PR

1. ✅ Run all accessibility tests: `./gradlew connectedAndroidTest`
2. ✅ Ensure no new accessibility violations are introduced
3. ✅ Add accessibility tests for new UI components
4. ✅ Verify all interactive elements have content descriptions
5. ✅ Check that touch targets are at least 48x48dp
6. ✅ Validate color contrast ratios meet WCAG 2.1 AA standards

### Writing Accessible Code

#### Content Descriptions

Always add content descriptions to interactive elements:

```kotlin
// ✅ Good
ImageButton(
    modifier = Modifier.contentDescription("Close dialog")
)

// ❌ Bad
ImageButton(
    // No content description
)
```

```xml
<!-- ✅ Good -->
<ImageButton
    android:id="@+id/closeButton"
    android:contentDescription="@string/close_button" />

<!-- ❌ Bad -->
<ImageButton
    android:id="@+id/closeButton" />
```

#### Touch Target Sizes

Ensure all interactive elements are at least 48x48dp:

```xml
<!-- ✅ Good -->
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:minWidth="48dp"
    android:minHeight="48dp" />

<!-- ❌ Bad -->
<Button
    android:layout_width="32dp"
    android:layout_height="32dp" />
```

#### Color Contrast

Use colors with sufficient contrast ratios:
- Normal text: Minimum 4.5:1
- Large text (18pt+): Minimum 3:1
- UI components: Minimum 3:1

Tools to check contrast:
- [WebAIM Contrast Checker](https://webaim.org/resources/contrastchecker/)
- Android Studio Layout Inspector

### Adding Accessibility Tests

When adding new features, include accessibility tests:

```kotlin
@Test
fun testAccessibility_newFeature() {
    onView(withId(R.id.newElement))
        .check(matches(isDisplayed()))
        .check(matches(withContentDescription("Expected description")))
}
```

## Code Style

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use Android Studio's built-in code formatter
- Keep functions small and focused
- Write meaningful comments for complex logic

## Pull Request Process

1. **Create a feature branch:**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes:**
   - Write clean, well-documented code
   - Add tests for new functionality
   - Ensure all tests pass

3. **Commit your changes:**
   ```bash
   git add .
   git commit -m "Add feature: description"
   ```

4. **Push to your fork:**
   ```bash
   git push origin feature/your-feature-name
   ```

5. **Open a Pull Request:**
   - Provide a clear description of changes
   - Reference any related issues
   - Ensure CI checks pass

## CI/CD Pipeline

Our GitHub Actions workflows automatically:
- Run CodeQL security analysis
- Execute accessibility tests
- Generate test reports
- Check for lint violations

All checks must pass before a PR can be merged.

## Need Help?

- 📖 Read [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md) for detailed accessibility guidelines
- 🐛 Open an issue for bug reports
- 💡 Open an issue for feature requests
- 📧 Contact maintainers for other questions

## Code of Conduct

- Be respectful and inclusive
- Provide constructive feedback
- Focus on the code, not the person
- Help others learn and grow

Thank you for contributing to making GitHub Mobile accessible to everyone! 🎉
