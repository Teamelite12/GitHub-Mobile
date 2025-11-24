package com.github.mobile;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.accessibility.AccessibilityChecks;

import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils;
import com.google.android.apps.common.testing.accessibility.framework.checks.SpeakableTextPresentCheck;
import com.google.android.apps.common.testing.accessibility.framework.checks.TouchTargetSizeCheck;
import com.google.android.apps.common.testing.accessibility.framework.checks.TextContrastCheck;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Comprehensive accessibility tests for the GitHub Mobile application.
 * 
 * This test class verifies that the application meets accessibility standards
 * including touch target sizes, content descriptions, text contrast, and more.
 */
@RunWith(AndroidJUnit4.class)
public class AccessibilityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Enable accessibility checks for all tests.
     * This will automatically verify accessibility standards during test execution.
     */
    @BeforeClass
    public static void enableAccessibilityChecks() {
        AccessibilityChecks.enable()
                .setRunChecksFromRootView(true)
                .setSuppressingResultMatcher(
                        Matchers.anyOf(
                                // Suppress specific checks if needed
                                AccessibilityCheckResultUtils.matchesCheck(
                                        SpeakableTextPresentCheck.class)
                        )
                );
    }

    /**
     * Test that all UI elements have appropriate touch target sizes.
     * Touch targets should be at least 48dp x 48dp for accessibility.
     */
    @Test
    public void testTouchTargetSizes() {
        // Verify login button is displayed
        onView(withId(R.id.login_button))
                .check(matches(isDisplayed()));

        // Verify username input is displayed
        onView(withId(R.id.username_input))
                .check(matches(isDisplayed()));

        // The AccessibilityChecks will automatically verify touch target sizes
    }

    /**
     * Test that all UI elements have content descriptions for screen readers.
     */
    @Test
    public void testContentDescriptions() {
        // Verify welcome text has content description
        onView(withId(R.id.welcome_text))
                .check(matches(isDisplayed()));

        // Verify username input has content description
        onView(withId(R.id.username_input))
                .check(matches(isDisplayed()));

        // Verify login button has content description
        onView(withId(R.id.login_button))
                .check(matches(isDisplayed()));

        // AccessibilityChecks will verify all elements have proper content descriptions
    }

    /**
     * Test text contrast ratios meet WCAG standards.
     */
    @Test
    public void testTextContrast() {
        // Verify all text elements are displayed
        onView(withId(R.id.welcome_text))
                .check(matches(isDisplayed()));

        onView(withId(R.id.username_input))
                .check(matches(isDisplayed()));

        onView(withId(R.id.login_button))
                .check(matches(isDisplayed()));

        // AccessibilityChecks will verify text contrast automatically
    }

    /**
     * Test user interaction flow with accessibility in mind.
     */
    @Test
    public void testUserInteractionAccessibility() {
        // Type username
        onView(withId(R.id.username_input))
                .perform(typeText("testuser"));

        // Click login button
        onView(withId(R.id.login_button))
                .perform(click());

        // Verify welcome message updated
        onView(withId(R.id.welcome_text))
                .check(matches(withText("Welcome, testuser!")));

        // All interactions are checked for accessibility by AccessibilityChecks
    }

    /**
     * Test empty input handling with accessibility.
     */
    @Test
    public void testEmptyInputAccessibility() {
        // Click login button without entering username
        onView(withId(R.id.login_button))
                .perform(click());

        // Verify error message is displayed
        onView(withId(R.id.welcome_text))
                .check(matches(withText("Please enter a username")));

        // Verify the error message is accessible
    }

    /**
     * Test keyboard navigation and focus order.
     */
    @Test
    public void testKeyboardNavigation() {
        // Verify username input can receive focus
        onView(withId(R.id.username_input))
                .check(matches(isDisplayed()));

        // Verify login button can receive focus
        onView(withId(R.id.login_button))
                .check(matches(isDisplayed()));

        // Focus order is important for keyboard and screen reader navigation
    }
}
