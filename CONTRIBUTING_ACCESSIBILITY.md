# Contributing to Accessibility

Thank you for your interest in making GitHub Mobile more accessible! This guide will help you contribute accessibility improvements to the project.

## Before You Start

1. Read [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md) to understand our accessibility standards
2. Review the [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
3. Familiarize yourself with [Android Accessibility](https://developer.android.com/guide/topics/ui/accessibility)

## Development Workflow

### 1. Setting Up Your Environment

```bash
# Clone the repository
git clone https://github.com/Teamelite12/GitHub-Mobile.git
cd GitHub-Mobile

# Build the project
./gradlew build

# Run tests
./test-accessibility.sh --all
```

### 2. Making Changes

When adding or modifying UI components:

#### Required Accessibility Attributes

✅ **Content Descriptions**: All interactive elements MUST have meaningful content descriptions
```xml
<Button
    android:id="@+id/my_button"
    android:contentDescription="@string/my_button_description"
    ... />
```

✅ **Touch Target Sizes**: Minimum 48dp x 48dp for all interactive elements
```xml
<Button
    android:minWidth="48dp"
    android:minHeight="48dp"
    ... />
```

✅ **Labels for Input Fields**: All EditText fields must have hints or labels
```xml
<EditText
    android:id="@+id/my_input"
    android:hint="@string/my_input_hint"
    android:contentDescription="@string/my_input_description"
    ... />
```

✅ **Text Sizing**: Use sp units for all text
```xml
<TextView
    android:textSize="16sp"
    ... />
```

✅ **Focus Order**: Ensure logical tab order for keyboard navigation
```xml
<Button
    android:nextFocusDown="@id/next_button"
    android:nextFocusUp="@id/previous_button"
    ... />
```

### 3. Writing Tests

Every new UI component or feature MUST include accessibility tests.

#### Example Accessibility Test

```java
@Test
public void testNewFeatureAccessibility() {
    // Verify element is displayed
    onView(withId(R.id.new_element))
            .check(matches(isDisplayed()));
    
    // Verify content description exists
    onView(withId(R.id.new_element))
            .check(matches(withContentDescription(notNullValue())));
    
    // Test interaction
    onView(withId(R.id.new_element))
            .perform(click());
    
    // Automated accessibility checks will run automatically
}
```

### 4. Testing Your Changes

Run the accessibility test script:
```bash
./test-accessibility.sh --all
```

Manual testing checklist:
- [ ] Test with TalkBack enabled
- [ ] Test with largest font size setting
- [ ] Test with color filters enabled
- [ ] Test keyboard navigation
- [ ] Verify touch target sizes
- [ ] Check text contrast ratios

### 5. Submitting a Pull Request

Before submitting:

1. **Run all tests**: `./test-accessibility.sh --all`
2. **Run lint checks**: `./gradlew lint`
3. **Manual TalkBack test**: Enable TalkBack and navigate through your changes
4. **Fill out accessibility section** in PR template:

```markdown
## Accessibility Checklist

- [ ] All new UI elements have content descriptions
- [ ] Touch targets meet 48dp minimum
- [ ] Text contrast meets WCAG standards
- [ ] Tested with TalkBack
- [ ] Tested with different font sizes
- [ ] Accessibility tests added/updated
- [ ] Lint checks pass
```

## Common Accessibility Issues and Fixes

### Issue: Missing Content Description
**Problem**: Image button has no content description
```xml
<ImageButton
    android:id="@+id/icon_button"
    android:src="@drawable/icon" />
```

**Fix**: Add content description
```xml
<ImageButton
    android:id="@+id/icon_button"
    android:src="@drawable/icon"
    android:contentDescription="@string/icon_button_desc" />
```

### Issue: Small Touch Target
**Problem**: Button is too small
```xml
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

**Fix**: Set minimum dimensions
```xml
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:minWidth="48dp"
    android:minHeight="48dp" />
```

### Issue: Poor Text Contrast
**Problem**: Light gray text on white background

**Fix**: Use Material Design colors with sufficient contrast
```xml
<color name="text_primary">#DE000000</color> <!-- 87% black -->
<color name="text_secondary">#99000000</color> <!-- 60% black -->
```

### Issue: Unlabeled Form Field
**Problem**: EditText has no hint
```xml
<EditText
    android:id="@+id/input_field" />
```

**Fix**: Add hint and content description
```xml
<EditText
    android:id="@+id/input_field"
    android:hint="@string/input_hint"
    android:contentDescription="@string/input_desc" />
```

## Resources

- [Android Accessibility Developer Guide](https://developer.android.com/guide/topics/ui/accessibility)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)
- [WCAG 2.1 Quick Reference](https://www.w3.org/WAI/WCAG21/quickref/)
- [Google Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor)
- [WebAIM Contrast Checker](https://webaim.org/resources/contrastchecker/)

## Getting Help

- Open an issue with the `accessibility` label
- Review existing accessibility tests in `app/src/androidTest/java/com/github/mobile/`
- Check the [ACCESSIBILITY_TESTING.md](ACCESSIBILITY_TESTING.md) guide

## Code Review

Accessibility is a key part of our code review process. PRs will be reviewed for:

1. **Compliance**: All changes meet WCAG 2.1 Level AA standards
2. **Testing**: Adequate accessibility tests are included
3. **Documentation**: Changes are documented if they introduce new patterns
4. **Compatibility**: Changes work with TalkBack and other assistive technologies

## Recognition

Contributors who make significant accessibility improvements will be recognized in our release notes and CONTRIBUTORS.md file.

Thank you for helping make GitHub Mobile accessible to everyone! 🎉
