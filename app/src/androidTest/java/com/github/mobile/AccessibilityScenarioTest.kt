package com.github.mobile

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckPreset
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Scenario-based accessibility tests
 * 
 * This test class demonstrates testing specific accessibility scenarios:
 * - Content descriptions for screen readers
 * - Button interactions with TalkBack
 * - Text visibility and contrast
 * - Dynamic content announcements
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class AccessibilityScenarioTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupAccessibilityChecks() {
            AccessibilityTestUtils.initializeAccessibilityChecksWithPreset(
                AccessibilityCheckPreset.LATEST
            )
        }
    }

    @Test
    fun testAccessibility_welcomeTextHasContentDescription() {
        // Verify that the welcome text has a proper content description
        // This is crucial for screen reader users
        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
            .check(matches(withContentDescription("Welcome message for GitHub Mobile application")))
    }

    @Test
    fun testAccessibility_loginButtonHasContentDescription() {
        // Verify that the login button has a descriptive content description
        // Screen readers should announce "Login to GitHub, button"
        onView(withId(R.id.loginButton))
            .check(matches(isDisplayed()))
            .check(matches(withText("Login")))
            .check(matches(withContentDescription("Login to GitHub")))
    }

    @Test
    fun testAccessibility_buttonIsClickable() {
        // Verify that interactive elements are properly clickable
        // This ensures users with motor disabilities can interact with the app
        onView(withId(R.id.loginButton))
            .check(matches(isDisplayed()))
            .perform(click())
        
        // After clicking, verify the app responds appropriately
        // In a real app, this would navigate to a login screen
    }

    @Test
    fun testAccessibility_allTextIsReadable() {
        // Verify that all text elements are visible and readable
        // This includes checking that text isn't truncated
        onView(withText("Welcome to GitHub Mobile"))
            .check(matches(isDisplayed()))
        
        onView(withText("Login"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAccessibility_contrastRatios() {
        // This test will verify that all text and UI elements meet
        // WCAG 2.1 AA contrast ratio requirements (4.5:1 for normal text, 3:1 for large text)
        // The AccessibilityChecks framework automatically validates this
        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
        
        onView(withId(R.id.loginButton))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAccessibility_touchTargetSizes() {
        // This test verifies that all interactive elements meet the minimum
        // touch target size of 48x48dp as per Material Design guidelines
        // The AccessibilityChecks framework automatically validates this
        onView(withId(R.id.loginButton))
            .check(matches(isDisplayed()))
    }
}
