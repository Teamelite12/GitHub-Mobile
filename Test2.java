public class Test2 {
    public static void main(String[] args) {
        System.out.println("Running Test2...");
        
        // Test the add method
        testAdd();
        
        // Test the greet method
        testGreet();
        
        System.out.println("All tests passed!");
    }

    public static void testAdd() {
        int result = main.add(2, 3);
        if (result != 5) {
            throw new AssertionError("Expected 5 but got " + result);
        }
        System.out.println("testAdd passed: 2 + 3 = " + result);
    }

    public static void testGreet() {
        String result = main.greet("World");
        String expected = "Hello, World!";
        if (!result.equals(expected)) {
            throw new AssertionError("Expected '" + expected + "' but got '" + result + "'");
        }
        System.out.println("testGreet passed: " + result);
    }
}
