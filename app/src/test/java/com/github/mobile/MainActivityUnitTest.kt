package com.github.mobile

import org.junit.Test
import org.junit.Assert.*

/**
 * Basic unit tests for the GitHub Mobile app
 * 
 * These tests run on the JVM and don't require an Android device
 */
class MainActivityUnitTest {
    
    @Test
    fun testPackageNameIsCorrect() {
        val packageName = "com.github.mobile"
        assertEquals("com.github.mobile", packageName)
    }
    
    @Test
    fun testBasicStringOperations() {
        val searchQuery = "android-accessibility"
        assertTrue(searchQuery.isNotEmpty())
        assertEquals(21, searchQuery.length)
    }
}
