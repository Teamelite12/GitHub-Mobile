package com.github.mobile;

import android.view.View;

/**
 * Utility class for accessibility-related helper methods.
 * This class provides methods to enhance app accessibility.
 */
public class AccessibilityUtils {

    /**
     * Minimum touch target size in dp as per Android accessibility guidelines.
     */
    public static final int MINIMUM_TOUCH_TARGET_SIZE_DP = 48;

    /**
     * Minimum text size in sp for readability.
     */
    public static final int MINIMUM_TEXT_SIZE_SP = 12;

    /**
     * Minimum color contrast ratio for normal text (WCAG AA).
     */
    public static final double MINIMUM_CONTRAST_RATIO = 4.5;

    /**
     * Checks if a view has a content description set.
     *
     * @param view The view to check
     * @return true if the view has a non-empty content description
     */
    public static boolean hasContentDescription(View view) {
        CharSequence contentDescription = view.getContentDescription();
        return contentDescription != null && contentDescription.length() > 0;
    }

    /**
     * Checks if a content description is meaningful (not just a single character).
     *
     * @param contentDescription The content description to check
     * @return true if the content description is meaningful
     */
    public static boolean isMeaningfulContentDescription(String contentDescription) {
        return contentDescription != null && contentDescription.length() > 5;
    }

    /**
     * Announces a message for screen readers.
     *
     * @param view The view to announce from
     * @param message The message to announce
     */
    public static void announce(View view, String message) {
        if (view != null && message != null) {
            view.announceForAccessibility(message);
        }
    }
}
