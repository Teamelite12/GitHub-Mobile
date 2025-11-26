/**
 * Main class for GitHub Mobile application.
 * 
 * Changes made:
 * - Renamed from 'main' to 'Main' (Java naming convention)
 * - Added constants for greeting strings (GREETING_PREFIX, GREETING_SUFFIX)
 * - Used simple string concatenation for compiler optimization
 */
public class Main {
    // Constants for greeting message construction
    private static final String GREETING_PREFIX = "Hello, ";
    private static final String GREETING_SUFFIX = "!";

    /**
     * Application entry point.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello from GitHub Mobile!");
    }

    /**
     * Adds two integers together.
     * @param a first integer
     * @param b second integer
     * @return sum of a and b
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Creates a greeting message for the given name.
     * @param name the name to greet
     * @return formatted greeting string
     */
    public static String greet(String name) {
        return GREETING_PREFIX + name + GREETING_SUFFIX;
    }
}
