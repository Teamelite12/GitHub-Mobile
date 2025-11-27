/**
 * Main class for GitHub Mobile application.
 */
public class Main {
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
     * @param name the name to greet (null returns "Hello!")
     * @return formatted greeting string
     */
    public static String greet(String name) {
        if (name == null) {
            return GREETING_PREFIX.trim() + GREETING_SUFFIX;
        }
        return GREETING_PREFIX + name + GREETING_SUFFIX;
    }
}
