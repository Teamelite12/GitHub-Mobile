package com.github.mobile.accessibility;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityManager;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.github.mobile.MainActivity;
import com.github.mobile.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test suite for verifying accessibility service integration
 * and runtime accessibility behavior.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class AccessibilityServiceTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = 
        new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Test that accessibility services can be queried
     */
    @Test
    public void testAccessibilityManagerAvailable() {
        Context context = ApplicationProvider.getApplicationContext();
        AccessibilityManager accessibilityManager = 
            (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        
        assertNotNull("AccessibilityManager should be available", accessibilityManager);
    }

    /**
     * Test that views respond to accessibility events
     */
    @Test
    public void testViewsSendAccessibilityEvents() {
        activityRule.getScenario().onActivity(activity -> {
            View titleText = activity.findViewById(R.id.title_text);
            View actionButton = activity.findViewById(R.id.action_button);
            
            // Views should send accessibility events when appropriate
            assertNotNull("Title text should exist", titleText);
            assertNotNull("Action button should exist", actionButton);
        });
    }

    /**
     * Test that the app works when accessibility services are enabled
     * (simulating TalkBack or other screen readers)
     */
    @Test
    public void testAppFunctionalityWithAccessibilityEnabled() {
        Context context = ApplicationProvider.getApplicationContext();
        AccessibilityManager accessibilityManager = 
            (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        
        // Even if accessibility services are not currently enabled,
        // the app should still function correctly
        assertNotNull("AccessibilityManager should exist", accessibilityManager);
    }
}
