# Accessibility Testing Checklist

Use this checklist when reviewing accessibility in the GitHub Mobile app.

## Visual Design
- [ ] Text has minimum contrast ratio of 4.5:1 for normal text, 3:1 for large text
- [ ] Color is not the only means of conveying information
- [ ] Focus indicators are clearly visible
- [ ] Text can be resized up to 200% without loss of content or functionality

## Touch Targets
- [ ] All interactive elements are at least 48dp x 48dp
- [ ] Adequate spacing between interactive elements (at least 8dp)
- [ ] Touch targets don't overlap

## Content
- [ ] All images have meaningful alternative text (contentDescription)
- [ ] Decorative images are marked as decorative (contentDescription="@null" or importantForAccessibility="no")
- [ ] All buttons have descriptive labels
- [ ] Form fields have associated labels or hints
- [ ] Error messages are clear and helpful

## Navigation
- [ ] Logical focus order (top to bottom, left to right)
- [ ] All interactive elements are keyboard accessible
- [ ] Focus moves predictably through the interface
- [ ] User can navigate away from all elements

## Screen Reader (TalkBack)
- [ ] All UI elements are announced correctly
- [ ] Announcements are clear and concise
- [ ] Custom views implement accessibility properly
- [ ] Dynamic content changes are announced
- [ ] No unnecessary announcements or redundant information

## Forms and Input
- [ ] All form fields have labels
- [ ] Required fields are clearly indicated
- [ ] Error messages are associated with their fields
- [ ] Input format requirements are clear
- [ ] Success messages are announced

## Multimedia
- [ ] Videos have captions
- [ ] Audio content has transcripts
- [ ] Auto-playing content can be paused

## Testing Methods
- [ ] Automated tests pass (Espresso Accessibility)
- [ ] Manual testing with TalkBack completed
- [ ] Testing with different font sizes completed
- [ ] Testing with color filters completed
- [ ] Testing with Switch Access (if applicable)

## Documentation
- [ ] Accessibility features are documented
- [ ] Known issues are tracked
- [ ] Workarounds are provided for known issues

## Notes
_Use this space to document specific issues or observations during testing_

---

**Tester:** _________________
**Date:** _________________
**Version:** _________________
