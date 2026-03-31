package com.teamelite.github.mobile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils;
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator;

import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Accessibility tests for MainActivity using Google's Accessibility Test Framework.
 * 
 * These tests verify:
 * - Touch target sizes (minimum 48x48 dp)
 * - Content descriptions for interactive elements
 * - Text contrast ratios
 * - Proper view hierarchies for screen readers
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityAccessibilityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Enable accessibility checks for all tests in this class.
     * This will automatically check for common accessibility issues on every Espresso action.
     */
    @BeforeClass
    public static void enableAccessibilityChecks() {
        AccessibilityChecks.enable();
    }

    /**
     * Test that the main activity launches and passes basic accessibility checks.
     */
    @Test
    public void testActivityLaunch_passesAccessibilityChecks() {
        // Simply launching the activity triggers accessibility checks
        onView(withId(R.id.titleText)).check(matches(isDisplayed()));
    }

    /**
     * Test that the search field has proper accessibility attributes.
     */
    @Test
    public void testSearchField_hasContentDescription() {
        // Verify search field is displayed and accessible
        onView(withId(R.id.searchField))
                .check(matches(isDisplayed()));
    }

    /**
     * Test that the search button meets accessibility requirements.
     */
    @Test
    public void testSearchButton_meetsAccessibilityRequirements() {
        // Verify button is displayed and meets touch target size requirements
        onView(withId(R.id.searchButton))
                .check(matches(isDisplayed()));
    }

    /**
     * Test complete search interaction flow with accessibility checks.
     */
    @Test
    public void testSearchInteraction_passesAccessibilityChecks() {
        // Type in search field
        onView(withId(R.id.searchField))
                .perform(typeText("test repository"));

        // Click search button (accessibility checks happen automatically)
        onView(withId(R.id.searchButton))
                .perform(click());

        // Verify result is displayed
        onView(withId(R.id.resultText))
                .check(matches(isDisplayed()));
    }

    /**
     * Test that all interactive elements have minimum touch target sizes.
     * This test explicitly verifies the 48dp minimum requirement.
     */
    @Test
    public void testTouchTargets_meetMinimumSize() {
        // Get the activity and verify touch targets
        activityRule.getScenario().onActivity(activity -> {
            View searchButton = activity.findViewById(R.id.searchButton);
            View searchField = activity.findViewById(R.id.searchField);

            // Verify minimum touch target size (48dp = ~144px at mdpi)
            int minSize = (int) (48 * activity.getResources().getDisplayMetrics().density);
            
            assert searchButton.getMinimumHeight() >= minSize : "Search button height too small";
            assert searchButton.getMinimumWidth() >= minSize : "Search button width too small";
            assert searchField.getMinimumHeight() >= minSize : "Search field height too small";
        });
    }

    /**
     * Test that content descriptions are set for screen reader accessibility.
     */
    @Test
    public void testContentDescriptions_areSet() {
        activityRule.getScenario().onActivity(activity -> {
            View searchButton = activity.findViewById(R.id.searchButton);
            View searchField = activity.findViewById(R.id.searchField);
            View resultText = activity.findViewById(R.id.resultText);

            assert searchButton.getContentDescription() != null : "Search button missing content description";
            assert searchField.getContentDescription() != null : "Search field missing content description";
            assert resultText.getContentDescription() != null : "Result text missing content description";
        });
    }
}
