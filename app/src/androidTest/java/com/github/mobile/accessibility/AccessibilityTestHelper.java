package com.github.mobile.accessibility;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;

import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckPreset;
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult;
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityViewCheckResult;
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator;

import org.hamcrest.Matcher;

import java.util.List;

/**
 * Helper class for accessibility testing in the GitHub Mobile application.
 * Provides utilities for checking accessibility compliance of UI elements.
 */
public class AccessibilityTestHelper {

    /**
     * Creates an AccessibilityValidator configured with recommended checks
     * 
     * @return Configured AccessibilityValidator instance
     */
    public static AccessibilityValidator createValidator() {
        return new AccessibilityValidator()
                .setCheckPreset(AccessibilityCheckPreset.LATEST)
                .setSuppressingResultMatcher(result -> {
                    // Suppress specific known issues if needed
                    // Example: return result.getClass() == SomeSpecificCheck.class;
                    return false;
                });
    }

    /**
     * Creates a ViewAction that enables accessibility checking for a specific view
     * 
     * @return ViewAction that can be used with Espresso
     */
    public static ViewAction enableAccessibilityChecking() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "Enable accessibility checking";
            }

            @Override
            public void perform(UiController uiController, View view) {
                // Accessibility checks are performed automatically by the validator
                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    /**
     * Verifies that a view has proper content description for screen readers
     * 
     * @param view The view to check
     * @return true if the view has a content description, false otherwise
     */
    public static boolean hasContentDescription(View view) {
        CharSequence contentDescription = view.getContentDescription();
        return contentDescription != null && contentDescription.length() > 0;
    }

    /**
     * Verifies that a view meets minimum touch target size requirements (48dp x 48dp)
     * 
     * @param view The view to check
     * @return true if the view meets minimum size requirements
     */
    public static boolean meetsTouchTargetSize(View view) {
        int minTouchTargetSize = 48; // 48dp minimum
        float density = view.getContext().getResources().getDisplayMetrics().density;
        int minTouchTargetSizePx = (int) (minTouchTargetSize * density);
        
        return view.getWidth() >= minTouchTargetSizePx && 
               view.getHeight() >= minTouchTargetSizePx;
    }

    /**
     * Checks if a view is important for accessibility
     * 
     * @param view The view to check
     * @return true if the view is marked as important for accessibility
     */
    public static boolean isImportantForAccessibility(View view) {
        return view.isImportantForAccessibility();
    }

    /**
     * Prints accessibility check results for debugging
     * 
     * @param results List of accessibility check results
     */
    public static void printAccessibilityResults(List<AccessibilityViewCheckResult> results) {
        for (AccessibilityViewCheckResult result : results) {
            if (result.getType() == AccessibilityCheckResult.AccessibilityCheckResultType.ERROR ||
                result.getType() == AccessibilityCheckResult.AccessibilityCheckResultType.WARNING) {
                System.out.println("Accessibility Issue: " + result.getMessage());
            }
        }
    }
}
