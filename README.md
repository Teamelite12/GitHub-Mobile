# GitHub Mobile - Accessibility Testing

This repository demonstrates accessibility testing best practices for Android applications using Google's Accessibility Test Framework.

## Overview

Accessibility testing ensures that mobile applications are usable by everyone, including people with disabilities who rely on assistive technologies like screen readers, switch controls, and voice access.

## Project Structure

```
GitHub-Mobile/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/github/mobile/
│   │   │   │   └── MainActivity.java          # Main activity with accessibility features
│   │   │   └── AndroidManifest.xml            # App manifest
│   │   └── androidTest/
│   │       └── java/com/github/mobile/
│   │           └── MainActivityAccessibilityTest.java  # Accessibility tests
│   └── build.gradle                           # App-level build configuration
├── build.gradle                               # Project-level build configuration
├── settings.gradle                            # Gradle settings
└── README.md                                  # This file
```

## Accessibility Features

The sample `MainActivity` demonstrates these accessibility best practices:

1. **Content Descriptions**: All UI elements have descriptive content descriptions for screen readers
2. **Touch Targets**: Interactive elements meet minimum size requirements (48dp x 48dp)
3. **Speakable Text**: All text is readable by assistive technologies
4. **Proper Labeling**: Elements are properly labeled for context

## Accessibility Testing

### Test Framework

This project uses:
- **Google Accessibility Test Framework (ATF)**: Automated accessibility checking
- **Espresso Accessibility**: Integration with Espresso testing framework
- **AndroidX Test**: Modern Android testing libraries

### Running Tests

To run the accessibility tests:

```bash
# Run all accessibility tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.MainActivityAccessibilityTest
```

### What Gets Tested

The accessibility tests automatically validate:

✅ **Content Labeling**: All elements have appropriate labels  
✅ **Touch Target Size**: Interactive elements are at least 48dp x 48dp  
✅ **Text Contrast**: Text has sufficient contrast with background  
✅ **Speakable Text**: All text is readable by screen readers  
✅ **Duplicate Labels**: No duplicate speakable text that could confuse users  

### Test Cases

1. **testMainActivityAccessibility**: Validates overall activity accessibility
2. **testContentDescriptionsPresent**: Ensures all elements have content descriptions
3. **testTouchTargetSizes**: Verifies minimum touch target sizes
4. **testSpeakableText**: Confirms text is readable by screen readers

## Accessibility Best Practices

### 1. Always Provide Content Descriptions

```java
// Good
Button button = new Button(this);
button.setText("Submit");
button.setContentDescription("Submit form button");

// Bad - missing content description
Button button = new Button(this);
button.setText("Submit");
```

### 2. Ensure Adequate Touch Target Sizes

Interactive elements should be at least 48dp x 48dp:

```java
// Good
button.setMinimumWidth(dpToPx(48));
button.setMinimumHeight(dpToPx(48));
```

### 3. Use Meaningful Labels

```java
// Good
textView.setContentDescription("Welcome message");

// Bad - too vague
textView.setContentDescription("Text");
```

### 4. Update Content Descriptions Dynamically

```java
// Update content description when state changes
actionButton.setOnClickListener(v -> {
    messageText.setText("Button clicked!");
    messageText.setContentDescription("Button was clicked message");
});
```

## Dependencies

```gradle
// Accessibility Testing Framework
androidTestImplementation 'com.google.android.apps.common.testing.accessibility.framework:accessibility-test-framework:4.0.0'

// Espresso Accessibility
androidTestImplementation 'androidx.test.espresso:espresso-accessibility:3.5.1'

// Core Espresso
androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
```

## CI/CD Integration

Accessibility tests can be integrated into CI/CD pipelines to ensure accessibility standards are maintained:

```yaml
# Example GitHub Actions workflow
- name: Run Accessibility Tests
  run: ./gradlew connectedAndroidTest
```

## Resources

- [Android Accessibility Overview](https://developer.android.com/guide/topics/ui/accessibility)
- [Accessibility Test Framework](https://github.com/google/Accessibility-Test-Framework-for-Android)
- [Espresso Accessibility](https://developer.android.com/training/testing/espresso/accessibility-checking)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)

## Building the Project

```bash
# Build the app
./gradlew build

# Run unit tests
./gradlew test

# Run instrumented tests (requires emulator or device)
./gradlew connectedAndroidTest
```

## Contributing

When contributing to this project, please ensure:

1. All new UI elements have content descriptions
2. Interactive elements meet minimum touch target sizes
3. Accessibility tests pass before submitting PRs
4. New features include accessibility test coverage

## License

This project is for demonstration purposes of accessibility testing best practices.
