#!/bin/bash
# Accessibility Testing Helper Script
# This script helps run various accessibility tests and checks

set -e

echo "========================================="
echo "GitHub Mobile Accessibility Test Runner"
echo "========================================="
echo ""

# Function to print section headers
print_header() {
    echo ""
    echo ">>> $1"
    echo "========================================="
}

# Check if gradlew exists
if [ ! -f "./gradlew" ]; then
    echo "Error: gradlew not found. Please run this script from the project root."
    exit 1
fi

# Make sure gradlew is executable
chmod +x ./gradlew

# Parse command line arguments
RUN_UNIT_TESTS=false
RUN_LINT=false
RUN_BUILD=false
RUN_INSTRUMENTED_TESTS=false
RUN_ALL=false

if [ $# -eq 0 ]; then
    RUN_ALL=true
fi

while [ $# -gt 0 ]; do
    case "$1" in
        --unit)
            RUN_UNIT_TESTS=true
            ;;
        --lint)
            RUN_LINT=true
            ;;
        --build)
            RUN_BUILD=true
            ;;
        --instrumented)
            RUN_INSTRUMENTED_TESTS=true
            ;;
        --all)
            RUN_ALL=true
            ;;
        --help)
            echo "Usage: $0 [options]"
            echo ""
            echo "Options:"
            echo "  --unit          Run unit tests"
            echo "  --lint          Run lint checks (accessibility focused)"
            echo "  --build         Build the debug APK"
            echo "  --instrumented  Run instrumented accessibility tests (requires emulator)"
            echo "  --all           Run all checks (default if no options specified)"
            echo "  --help          Show this help message"
            echo ""
            echo "Examples:"
            echo "  $0 --unit --lint    # Run unit tests and lint"
            echo "  $0 --all            # Run all checks"
            exit 0
            ;;
        *)
            echo "Unknown option: $1"
            echo "Use --help for usage information"
            exit 1
            ;;
    esac
    shift
done

# If all flag is set, enable everything except instrumented tests
if [ "$RUN_ALL" = true ]; then
    RUN_UNIT_TESTS=true
    RUN_LINT=true
    RUN_BUILD=true
fi

# Run unit tests
if [ "$RUN_UNIT_TESTS" = true ]; then
    print_header "Running Unit Tests"
    ./gradlew test --stacktrace
    echo "✅ Unit tests completed"
fi

# Run lint checks
if [ "$RUN_LINT" = true ]; then
    print_header "Running Lint Checks (Accessibility Focus)"
    ./gradlew lint --stacktrace
    
    # Check if lint report exists and show its location
    if [ -f "app/build/reports/lint-results.html" ]; then
        echo "✅ Lint checks completed"
        echo "📄 Report available at: app/build/reports/lint-results.html"
    else
        echo "⚠️  Lint report not found"
    fi
fi

# Build the APK
if [ "$RUN_BUILD" = true ]; then
    print_header "Building Debug APK"
    ./gradlew assembleDebug --stacktrace
    
    if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
        echo "✅ Build completed successfully"
        echo "📦 APK location: app/build/outputs/apk/debug/app-debug.apk"
    else
        echo "⚠️  APK not found at expected location"
    fi
fi

# Run instrumented tests
if [ "$RUN_INSTRUMENTED_TESTS" = true ]; then
    print_header "Running Instrumented Accessibility Tests"
    echo "⚠️  This requires a running Android emulator or connected device"
    
    # Check if adb can find devices
    if command -v adb &> /dev/null; then
        DEVICE_COUNT=$(adb devices | grep -v "List" | grep "device" | wc -l)
        if [ "$DEVICE_COUNT" -eq 0 ]; then
            echo "❌ No Android devices or emulators found"
            echo "   Please start an emulator or connect a device"
            exit 1
        fi
    fi
    
    ./gradlew connectedAndroidTest --stacktrace
    echo "✅ Instrumented tests completed"
    
    # Show test results location
    if [ -d "app/build/reports/androidTests/connected" ]; then
        echo "📄 Test reports available at: app/build/reports/androidTests/connected/"
    fi
fi

# Summary
print_header "Summary"
echo "All requested checks completed successfully! ✅"
echo ""
echo "For detailed results, check:"
echo "  - Unit test results: app/build/reports/tests/"
echo "  - Lint results: app/build/reports/lint-results.html"
echo "  - Build outputs: app/build/outputs/"
echo ""
echo "To test accessibility manually:"
echo "  1. Install the APK on a device"
echo "  2. Enable TalkBack in Settings > Accessibility"
echo "  3. Navigate through the app using TalkBack"
echo ""
echo "See ACCESSIBILITY_TESTING.md for more information"
