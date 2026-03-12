public class Test1 {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testAdd();
        testAddNegative();
        testAddZero();
        printResults();
    }

    private static void testAdd() {
        assertEquals(5, Main.add(2, 3), "testAdd");
    }

    private static void testAddNegative() {
        assertEquals(-1, Main.add(-3, 2), "testAddNegative");
    }

    private static void testAddZero() {
        assertEquals(0, Main.add(0, 0), "testAddZero");
    }

    private static void assertEquals(int expected, int actual, String testName) {
        if (expected == actual) {
            passed++;
            System.out.println(testName + ": PASSED");
        } else {
            failed++;
            System.out.println(testName + ": FAILED (expected " + expected + " but got " + actual + ")");
        }
    }

    private static void printResults() {
        System.out.println("\nResults: " + passed + " passed, " + failed + " failed");
        if (failed > 0) {
            throw new RuntimeException("Tests failed: " + failed);
        }
    }
}
