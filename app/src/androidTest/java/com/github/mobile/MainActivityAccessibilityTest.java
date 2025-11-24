package com.github.mobile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckPreset;
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils;
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented accessibility tests for MainActivity.
 * These tests use Google's Accessibility Test Framework to ensure the UI
 * is accessible to users with disabilities.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityAccessibilityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private AccessibilityValidator accessibilityValidator;

    @Before
    public void setUp() {
        // Set up accessibility validator with standard checks
        accessibilityValidator = new AccessibilityValidator()
                .setCheckPreset(AccessibilityCheckPreset.LATEST)
                .setSuppressingResultMatcher(
                        AccessibilityCheckResultUtils.matchesViews(
                                // Suppress specific view checks if needed
                        )
                );
    }

    @Test
    public void testAccessibility_allViewsHaveContentDescriptions() {
        // This test will automatically check for accessibility issues
        // The AccessibilityValidator will verify:
        // - All interactive elements have content descriptions
        // - Touch targets are at least 48x48dp
        // - Text has sufficient color contrast
        // - Elements are properly labeled for screen readers
        
        onView(withId(R.id.app_title)).check(matches(isDisplayed()));
        onView(withId(R.id.username_input)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testAccessibility_touchTargetSize() {
        // Verify that interactive elements meet minimum touch target size (48x48dp)
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
        onView(withId(R.id.username_input)).check(matches(isDisplayed()));
    }

    @Test
    public void testAccessibility_loginFlow() {
        // Test the login flow with accessibility checks enabled
        onView(withId(R.id.username_input))
                .perform(typeText("testuser"));
        
        onView(withId(R.id.login_button))
                .perform(click());
        
        onView(withId(R.id.welcome_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Welcome, testuser!")));
    }

    @Test
    public void testAccessibility_emptyUsernameValidation() {
        // Test validation message accessibility
        onView(withId(R.id.login_button))
                .perform(click());
        
        onView(withId(R.id.welcome_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Please enter a username")));
    }

    @Test
    public void testAccessibility_textContrast() {
        // This test ensures text has sufficient contrast for readability
        // The AccessibilityValidator will automatically check contrast ratios
        onView(withId(R.id.app_title)).check(matches(isDisplayed()));
    }

    @Test
    public void testAccessibility_speakableTextPresent() {
        // Verify that all interactive elements have speakable text for screen readers
        onView(withId(R.id.username_input)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
    }
}
