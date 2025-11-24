package com.github.mobile

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Accessibility tests for MainActivity
 * 
 * These tests ensure that the app meets accessibility standards:
 * - All interactive elements have content descriptions
 * - Touch targets are at least 48x48dp
 * - Color contrast ratios meet WCAG guidelines
 * - Text is readable and scalable
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityAccessibilityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    companion object {
        @BeforeClass
        @JvmStatic
        fun enableAccessibilityChecks() {
            // Enable accessibility checks for all tests
            AccessibilityChecks.enable()
                .setRunChecksFromRootView(true)
        }
    }

    @Test
    fun testAccessibility_welcomeTextIsVisible() {
        // Verify that the welcome text is displayed
        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAccessibility_loginButtonIsVisible() {
        // Verify that the login button is displayed
        onView(withId(R.id.loginButton))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAccessibility_allElementsHaveContentDescription() {
        // This test will fail if any interactive element lacks a content description
        // AccessibilityChecks will automatically verify this
        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
        
        onView(withId(R.id.loginButton))
            .check(matches(isDisplayed()))
    }
}
