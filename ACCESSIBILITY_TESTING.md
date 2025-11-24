# Accessibility Testing Guide

## Introduction

This guide provides comprehensive instructions for testing the accessibility of the GitHub Mobile application.

## Automated Testing

### Google Accessibility Test Framework

The project uses Google's Accessibility Test Framework integrated with Espresso tests. This framework automatically checks for common accessibility issues:

#### What it Checks
- **Content Labels**: Ensures all interactive elements have content descriptions
- **Touch Target Size**: Verifies touch targets are at least 48x48dp
- **Text Contrast**: Checks color contrast ratios meet WCAG standards
- **Clickable Span**: Validates clickable spans are properly implemented
- **Text Size**: Ensures text is readable (minimum size requirements)

#### Running Automated Tests
```bash
# Run all tests
./gradlew test connectedAndroidTest

# Run only accessibility tests
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.github.mobile.MainActivityAccessibilityTest
```

## Manual Testing with TalkBack

### Setting Up TalkBack
1. Go to Settings > Accessibility > TalkBack
2. Turn on TalkBack
3. Learn the TalkBack gestures:
   - Swipe right: Next item
   - Swipe left: Previous item
   - Double-tap: Activate
   - Two-finger swipe down: Read from top

### Testing Checklist with TalkBack

#### Navigation
- [ ] Can you navigate to all interactive elements?
- [ ] Are elements announced in a logical order?
- [ ] Do custom views announce their state correctly?

#### Content Descriptions
- [ ] Do all buttons have meaningful descriptions?
- [ ] Do all images have alternative text?
- [ ] Are decorative images marked as not important for accessibility?

#### Dynamic Content
- [ ] Are loading states announced?
- [ ] Are error messages announced automatically?
- [ ] Are success confirmations announced?

#### Forms
- [ ] Are all input fields properly labeled?
- [ ] Are hints and errors announced?
- [ ] Is form validation accessible?

## Accessibility Scanner

### Using Accessibility Scanner
1. Install Accessibility Scanner from Google Play
2. Enable the scanner in Settings > Accessibility
3. Navigate to your app screen
4. Tap the Accessibility Scanner button
5. Review the suggestions

### Common Issues to Check
- Touch target size too small
- Low contrast text
- Missing content descriptions
- Overlapping touch targets

## Testing Different User Scenarios

### Vision Impairments

#### Screen Reader Users
- Test entire app with TalkBack enabled
- Verify all content is accessible via swipe navigation
- Check that dynamic updates are announced

#### Low Vision Users
- Test with large text settings (Settings > Display > Font size)
- Test at 200% zoom level
- Verify color contrast is sufficient
- Test in bright sunlight and low light conditions

#### Color Blind Users
- Ensure information is not conveyed by color alone
- Test with color blindness simulators
- Verify patterns or text labels supplement color

### Motor Impairments

#### Limited Dexterity
- Verify all touch targets are at least 48x48dp
- Check spacing between interactive elements
- Test with Switch Access enabled
- Verify support for external keyboards

#### Switch Access Testing
1. Enable Switch Access in Settings > Accessibility
2. Configure switches (can use volume keys for testing)
3. Navigate through the app
4. Verify all actions are reachable

## Keyboard Navigation

### Testing Steps
1. Connect an external keyboard or enable on-screen keyboard
2. Use Tab key to navigate between elements
3. Use Enter to activate buttons
4. Use arrow keys for scrollable content

### Checklist
- [ ] Can you reach all interactive elements?
- [ ] Is the focus indicator clearly visible?
- [ ] Is tab order logical?
- [ ] Can you operate all custom controls?

## Testing with Different Settings

### Font Scaling
Test at different text size settings:
- Small
- Default
- Large
- Largest
- 200% (accessibility setting)

### Display Settings
- Dark mode
- High contrast mode
- Grayscale
- Color inversion

## Continuous Accessibility Testing

### Pre-Release Checklist
- [ ] All automated accessibility tests pass
- [ ] Manual TalkBack testing completed
- [ ] Accessibility Scanner run with no critical issues
- [ ] Testing with large text completed
- [ ] Color contrast verified
- [ ] Keyboard navigation tested

### Regular Maintenance
- Run automated tests on every commit (CI/CD)
- Manual accessibility audit quarterly
- User testing with people who use assistive technologies
- Stay updated on accessibility guidelines and tools

## Common Accessibility Issues and Fixes

### Issue: Missing Content Description
**Problem**: Interactive element without description
**Fix**: Add `android:contentDescription` in XML or `setContentDescription()` in code

### Issue: Touch Target Too Small
**Problem**: Button smaller than 48x48dp
**Fix**: Set `android:minWidth="48dp"` and `android:minHeight="48dp"`

### Issue: Low Contrast
**Problem**: Text hard to read on background
**Fix**: Adjust colors to achieve 4.5:1 ratio (use online contrast checkers)

### Issue: No Focus Indicator
**Problem**: Can't see which element has focus
**Fix**: Define custom focus drawable in styles

### Issue: Unlabeled Form Field
**Problem**: Input field purpose unclear to screen reader
**Fix**: Add `android:hint` or associate with a label using `android:labelFor`

## Resources

- [Android Accessibility Testing](https://developer.android.com/guide/topics/ui/accessibility/testing)
- [Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Material Design Accessibility](https://material.io/design/usability/accessibility.html)

## Getting Help

If you encounter accessibility issues:
1. Check the Android Accessibility documentation
2. Review WCAG guidelines
3. Test with actual users who use assistive technologies
4. Consult with accessibility experts
