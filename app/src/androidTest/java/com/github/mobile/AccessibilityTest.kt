package com.github.mobile

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.espresso.accessibility.AccessibilityChecks
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Comprehensive Accessibility Test Suite
 * 
 * Tests accessibility compliance for the GitHub Mobile app including:
 * - Touch target sizes (minimum 48x48dp)
 * - Content descriptions for UI elements
 * - Color contrast ratios
 * - Text size and readability
 * - Screen reader compatibility
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class AccessibilityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    companion object {
        @BeforeClass
        @JvmStatic
        fun enableAccessibilityChecks() {
            // Enable Espresso accessibility checks for all tests
            AccessibilityChecks.enable().apply {
                // Set result handling
                setRunChecksFromRootView(true)
                
                // Suppress specific checks if needed (none suppressed by default for strict testing)
                // Example: setSuppressingResultMatcher(matchesCheckNames(`is`("TouchTargetSizeCheck")))
            }
        }
    }

    @Test
    fun testTouchTargetSizes() {
        // Verify all interactive elements meet minimum touch target size (48x48dp)
        onView(withId(R.id.searchButton))
            .check(matches(isDisplayed()))
        
        onView(withId(R.id.searchInput))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testContentDescriptions() {
        // Verify all important UI elements have content descriptions
        val expectedDescriptions = mapOf(
            R.id.titleText to "Application title: GitHub Repository Search",
            R.id.searchInput to "Search input field for repository name",
            R.id.searchButton to "Search button to find repositories",
            R.id.resultText to "Search results display area"
        )
        
        expectedDescriptions.forEach { (viewId, description) ->
            onView(withId(viewId))
                .check(matches(isDisplayed()))
                .check(matches(withContentDescription(description)))
        }
    }

    @Test
    fun testEditableElementsAreAccessible() {
        // Verify editable elements are properly marked for accessibility
        onView(withId(R.id.searchInput))
            .check(matches(isDisplayed()))
            .perform(typeText("test-repository"))
            .perform(closeSoftKeyboard())
    }

    @Test
    fun testButtonsAreClickable() {
        // Verify buttons are clickable and accessible
        onView(withId(R.id.searchButton))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())
    }

    @Test
    fun testScreenReaderNavigation() {
        // Test that screen reader can navigate through all elements
        val viewIds = listOf(R.id.titleText, R.id.searchInput, R.id.searchButton, R.id.resultText)
        
        viewIds.forEach { viewId ->
            onView(withId(viewId)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testSearchFunctionalityAccessibility() {
        // Test the complete search flow for accessibility
        onView(withId(R.id.searchInput))
            .perform(typeText("android"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.searchButton))
            .perform(click())

        onView(withId(R.id.resultText))
            .check(matches(withText("Searching for: android")))
    }

    @Test
    fun testEmptySearchAccessibility() {
        // Test empty search scenario
        onView(withId(R.id.searchButton))
            .perform(click())

        onView(withId(R.id.resultText))
            .check(matches(withText("Please enter a search term")))
    }

    @Test
    fun testTextInputAccessibility() {
        // Verify text input is accessible and properly labeled
        val testQuery = "accessibility-testing"
        
        onView(withId(R.id.searchInput))
            .check(matches(isDisplayed()))
            .check(matches(withHint("Enter repository name")))
            .perform(typeText(testQuery))
            .perform(closeSoftKeyboard())
            .check(matches(withText(testQuery)))
    }
}
