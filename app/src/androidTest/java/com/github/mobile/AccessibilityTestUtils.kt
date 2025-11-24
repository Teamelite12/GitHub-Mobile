package com.github.mobile

import androidx.test.espresso.accessibility.AccessibilityChecks
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckPreset
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityViewCheckResult
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Accessibility testing utilities and custom matchers
 * 
 * This object provides helper methods and custom matchers for accessibility testing.
 * Use these utilities to:
 * - Configure accessibility checks with custom presets
 * - Suppress specific accessibility warnings
 * - Create custom accessibility matchers
 */
object AccessibilityTestUtils {

    /**
     * Initialize accessibility checks with default configuration
     * Call this in @BeforeClass of your test classes
     */
    fun initializeAccessibilityChecks() {
        AccessibilityChecks.enable()
            .setRunChecksFromRootView(true)
            .setSuppressingResultMatcher(null)
    }

    /**
     * Initialize accessibility checks with specific preset
     * @param preset The accessibility check preset to use (LATEST, NO_LAYOUT_CHECKS, etc.)
     */
    fun initializeAccessibilityChecksWithPreset(preset: AccessibilityCheckPreset) {
        AccessibilityChecks.enable()
            .setRunChecksFromRootView(true)
            .checkPreset(preset)
    }

    /**
     * Matcher to suppress specific accessibility check results
     * Useful when you need to suppress certain checks that may not be relevant
     * 
     * Example usage:
     * AccessibilityChecks.enable()
     *     .setSuppressingResultMatcher(suppressCheck("TouchTargetSizeCheck"))
     */
    fun suppressCheck(checkName: String): Matcher<in AccessibilityCheckResult> {
        return object : TypeSafeMatcher<AccessibilityCheckResult>() {
            override fun describeTo(description: Description) {
                description.appendText("Suppressing check: $checkName")
            }

            override fun matchesSafely(result: AccessibilityCheckResult): Boolean {
                return result.sourceCheckClass.simpleName == checkName
            }
        }
    }

    /**
     * Matcher to suppress accessibility checks for specific views
     * Use this when you want to exclude certain views from accessibility validation
     * 
     * Example usage:
     * AccessibilityChecks.enable()
     *     .setSuppressingResultMatcher(suppressViewById(R.id.decorativeImage))
     */
    fun suppressViewById(viewId: Int): Matcher<in AccessibilityCheckResult> {
        return object : TypeSafeMatcher<AccessibilityViewCheckResult>() {
            override fun describeTo(description: Description) {
                description.appendText("Suppressing view with ID: $viewId")
            }

            override fun matchesSafely(result: AccessibilityViewCheckResult): Boolean {
                return result.view.id == viewId
            }
        }
    }
}
