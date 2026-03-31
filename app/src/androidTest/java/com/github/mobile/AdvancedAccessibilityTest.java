package com.github.mobile;

import android.view.View;
import android.widget.EditText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Advanced accessibility tests focusing on specific accessibility features.
 * 
 * These tests go beyond basic checks to ensure comprehensive accessibility support.
 */
@RunWith(AndroidJUnit4.class)
public class AdvancedAccessibilityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Verify that all text input fields have hints or labels.
     */
    @Test
    public void testInputFieldsHaveHints() {
        activityRule.getScenario().onActivity(activity -> {
            EditText usernameInput = activity.findViewById(R.id.username_input);
            assertNotNull("Username input should exist", usernameInput);
            
            // Check that hint is set
            CharSequence hint = usernameInput.getHint();
            assertNotNull("Username input should have a hint", hint);
            assertTrue("Hint should not be empty", hint.length() > 0);
        });
    }

    /**
     * Verify that all interactive elements have content descriptions.
     */
    @Test
    public void testInteractiveElementsHaveContentDescriptions() {
        // Check login button has content description
        onView(withId(R.id.login_button))
                .check(matches(withContentDescription(notNullValue())));

        // Check username input has content description
        onView(withId(R.id.username_input))
                .check(matches(withContentDescription(notNullValue())));
    }

    /**
     * Verify that text views used for important information have content descriptions.
     */
    @Test
    public void testTextViewsHaveContentDescriptions() {
        onView(withId(R.id.welcome_text))
                .check(matches(withContentDescription(notNullValue())));
    }

    /**
     * Test that views are properly focusable for keyboard navigation.
     */
    @Test
    public void testFocusableElements() {
        activityRule.getScenario().onActivity(activity -> {
            View usernameInput = activity.findViewById(R.id.username_input);
            View loginButton = activity.findViewById(R.id.login_button);

            // Verify elements are focusable
            assertTrue("Username input should be focusable", usernameInput.isFocusable());
            assertTrue("Login button should be focusable", loginButton.isFocusable());

            // Verify elements can receive focus
            assertTrue("Username input should be able to take focus", 
                    usernameInput.isFocusableInTouchMode());
        });
    }

    /**
     * Test that the layout supports different text sizes (scalability).
     */
    @Test
    public void testLayoutSupportsScaling() {
        activityRule.getScenario().onActivity(activity -> {
            EditText usernameInput = activity.findViewById(R.id.username_input);
            
            // Verify text size is set in sp (scalable pixels)
            // This is verified at compile time by lint, but we can check it's set
            float textSize = usernameInput.getTextSize();
            assertTrue("Text size should be set", textSize > 0);
        });
    }

    /**
     * Verify that important for accessibility is properly set.
     */
    @Test
    public void testImportantForAccessibility() {
        activityRule.getScenario().onActivity(activity -> {
            View usernameInput = activity.findViewById(R.id.username_input);
            
            // Should be important for accessibility
            int importance = usernameInput.getImportantForAccessibility();
            assertTrue("Username input should be important for accessibility",
                    importance == View.IMPORTANT_FOR_ACCESSIBILITY_YES ||
                    importance == View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
        });
    }

    /**
     * Test minimum touch target dimensions.
     */
    @Test
    public void testMinimumTouchTargetDimensions() {
        activityRule.getScenario().onActivity(activity -> {
            View loginButton = activity.findViewById(R.id.login_button);
            View usernameInput = activity.findViewById(R.id.username_input);

            // Convert 48dp to pixels
            float density = activity.getResources().getDisplayMetrics().density;
            int minTouchTarget = (int) (48 * density);

            // Verify minimum dimensions are set
            assertTrue("Login button height should be at least 48dp",
                    loginButton.getMinimumHeight() >= minTouchTarget);
            assertTrue("Username input height should be at least 48dp",
                    usernameInput.getMinimumHeight() >= minTouchTarget);
        });
    }

    /**
     * Test that disabled state is properly communicated.
     */
    @Test
    public void testDisabledStateAccessibility() {
        activityRule.getScenario().onActivity(activity -> {
            View loginButton = activity.findViewById(R.id.login_button);
            
            // Temporarily disable button
            loginButton.setEnabled(false);
            
            // Verify it's not clickable when disabled
            assertTrue("Disabled button should not be clickable", !loginButton.isClickable());
            
            // Re-enable for other tests
            loginButton.setEnabled(true);
        });
    }
}
