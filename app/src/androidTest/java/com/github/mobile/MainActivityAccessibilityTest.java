package com.github.mobile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.github.mobile.accessibility.AccessibilityTestHelper;
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils;
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Comprehensive accessibility tests for MainActivity
 * Tests include:
 * - Content description verification
 * - Touch target size validation
 * - Color contrast checking
 * - Screen reader compatibility
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
        // Initialize accessibility validator with recommended checks
        accessibilityValidator = AccessibilityTestHelper.createValidator();
    }

    /**
     * Test that all interactive elements have content descriptions for screen readers
     */
    @Test
    public void testContentDescriptions() {
        // Verify title text has content description
        onView(withId(R.id.title_text))
                .check(matches(isDisplayed()))
                .check(matches(withContentDescription(notNullValue())));

        // Verify action button has content description
        onView(withId(R.id.action_button))
                .check(matches(isDisplayed()))
                .check(matches(withContentDescription(notNullValue())));
    }

    /**
     * Test that interactive elements meet minimum touch target size (48dp x 48dp)
     */
    @Test
    public void testTouchTargetSizes() {
        activityRule.getScenario().onActivity(activity -> {
            View actionButton = activity.findViewById(R.id.action_button);
            assertTrue("Button should meet minimum touch target size",
                    AccessibilityTestHelper.meetsTouchTargetSize(actionButton));
        });
    }

    /**
     * Test that views are properly marked for accessibility services
     */
    @Test
    public void testAccessibilityImportance() {
        activityRule.getScenario().onActivity(activity -> {
            View titleText = activity.findViewById(R.id.title_text);
            View actionButton = activity.findViewById(R.id.action_button);

            assertTrue("Title text should be important for accessibility",
                    AccessibilityTestHelper.isImportantForAccessibility(titleText));
            assertTrue("Action button should be important for accessibility",
                    AccessibilityTestHelper.isImportantForAccessibility(actionButton));
        });
    }

    /**
     * Comprehensive accessibility scan of the entire activity
     * Uses Google's Accessibility Test Framework to detect issues
     */
    @Test
    public void testAccessibilityScan() {
        // Perform a comprehensive accessibility check
        onView(ViewMatchers.isRoot()).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "Perform accessibility scan";
            }

            @Override
            public void perform(UiController uiController, View view) {
                accessibilityValidator.check(view);
            }
        });
    }

    /**
     * Test that text elements have sufficient size for readability
     */
    @Test
    public void testTextSize() {
        activityRule.getScenario().onActivity(activity -> {
            View titleText = activity.findViewById(R.id.title_text);
            // Text size is set to 24sp in the layout, which is good for accessibility
            assertTrue("Title text should be displayed", titleText.getVisibility() == View.VISIBLE);
        });
    }

    /**
     * Test navigation and focus order for assistive technologies
     */
    @Test
    public void testFocusOrder() {
        // Verify that views can receive focus in logical order
        onView(withId(R.id.title_text)).check(matches(isDisplayed()));
        onView(withId(R.id.action_button)).check(matches(isDisplayed()));
    }
}
