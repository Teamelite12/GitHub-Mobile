/**
 * Test3 - Test suite for Main class functionality.
 */
public class Test3 {
    private static int passedTests = 0;
    private static int totalTests = 0;

    /**
     * Main entry point - runs all tests and displays results.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Running Test3...\n");

        testAdd();
        testGreet();

        System.out.printf("%nResults: %d/%d tests passed%n", passedTests, totalTests);
        
        if (passedTests != totalTests) {
            System.exit(1);
        }
    }

    /**
     * Tests the Main.add() method.
     * Verifies that 2 + 3 = 5.
     */
    public static void testAdd() {
        totalTests++;
        final int a = 2, b = 3, expected = 5;
        int result = Main.add(a, b);
        
        if (result != expected) {
            System.out.printf("✗ testAdd: Expected %d but got %d%n", expected, result);
            return;
        }
        passedTests++;
        System.out.printf("✓ testAdd: %d + %d = %d%n", a, b, result);
    }

    /**
     * Tests the Main.greet() method.
     * Verifies that greet("World") returns "Hello, World!".
     */
    public static void testGreet() {
        totalTests++;
        final String name = "World";
        final String expected = "Hello, World!";
        String result = Main.greet(name);
        
        if (!expected.equals(result)) {
            System.out.printf("✗ testGreet: Expected '%s' but got '%s'%n", expected, result);
            return;
        }
        passedTests++;
        System.out.printf("✓ testGreet: greet(\"%s\") = \"%s\"%n", name, result);
    }
}
