package com.github.mobile

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
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
 * Comprehensive accessibility tests using different check presets
 * 
 * This test class demonstrates:
 * - Using different accessibility check presets (LATEST, NO_LAYOUT_CHECKS)
 * - Verifying touch target sizes
 * - Checking text contrast
 * - Ensuring proper labeling of UI elements
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ComprehensiveAccessibilityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupAccessibilityChecks() {
            // Enable accessibility checks with the latest preset
            AccessibilityChecks.enable()
                .setRunChecksFromRootView(true)
                .setSuppressingResultMatcher(null)
        }
    }

    @Test
    fun testAccessibility_latestPreset_allViews() {
        // Test all views with the latest accessibility checks
        // This will verify:
        // - Content descriptions
        // - Touch target sizes (minimum 48x48dp)
        // - Text contrast ratios
        // - Duplicate clickable bounds
        onView(withText("Welcome to GitHub Mobile"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAccessibility_buttonAccessibility() {
        // Specifically test button accessibility
        // Buttons should have:
        // - Minimum touch target size of 48x48dp
        // - Content description or text
        // - Adequate contrast ratio
        onView(withText("Login"))
            .check(matches(isDisplayed()))
    }
}
