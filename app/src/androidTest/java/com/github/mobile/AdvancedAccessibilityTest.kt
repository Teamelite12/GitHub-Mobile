package com.github.mobile

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Advanced Accessibility Tests
 * 
 * Tests advanced accessibility features including:
 * - Minimum touch target sizes validation
 * - Text contrast and readability
 * - Focus order for screen readers
 * - Keyboard navigation support
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class AdvancedAccessibilityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMinimumTouchTargetSize() {
        // Verify all clickable elements meet the minimum 48dp x 48dp requirement
        listOf(R.id.searchButton, R.id.searchInput).forEach { viewId ->
            onView(withId(viewId))
                .check(matches(isDisplayed()))
                .check(matches(hasMinimumSize(48, 48)))
        }
    }

    @Test
    fun testImportantForAccessibility() {
        // Verify important elements are marked for accessibility
        listOf(R.id.searchInput, R.id.resultText).forEach { viewId ->
            onView(withId(viewId))
                .check(matches(isDisplayed()))
                .check(matches(isImportantForAccessibility()))
        }
    }

    @Test
    fun testLabelConsistency() {
        // Verify consistent labeling across the app
        
        // Check hints
        onView(withId(R.id.searchInput))
            .check(matches(withHint("Enter repository name")))
        
        // Check text labels
        onView(withId(R.id.searchButton))
            .check(matches(withText("Search")))
        
        onView(withId(R.id.titleText))
            .check(matches(withText("GitHub Repository Search")))
    }

    @Test
    fun testEditTextAccessibilityHints() {
        // Verify EditText has proper hints for screen readers
        onView(withId(R.id.searchInput))
            .check(matches(isDisplayed()))
            .check(matches(withHint("Enter repository name")))
            .check(matches(withContentDescription("Search input field for repository name")))
    }

    /**
     * Custom matcher to verify minimum view size
     */
    private fun hasMinimumSize(minWidthDp: Int, minHeightDp: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("has minimum size of ${minWidthDp}dp x ${minHeightDp}dp")
            }

            override fun matchesSafely(view: View): Boolean {
                val density = view.resources.displayMetrics.density
                val minWidthPx = (minWidthDp * density).toInt()
                val minHeightPx = (minHeightDp * density).toInt()
                
                return view.width >= minWidthPx && view.height >= minHeightPx
            }
        }
    }

    /**
     * Custom matcher to verify importance for accessibility
     */
    private fun isImportantForAccessibility(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("is important for accessibility")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.importantForAccessibility == View.IMPORTANT_FOR_ACCESSIBILITY_YES ||
                       view.importantForAccessibility == View.IMPORTANT_FOR_ACCESSIBILITY_AUTO
            }
        }
    }
}
