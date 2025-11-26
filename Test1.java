public class Test1 {
    public static void main(String[] args) {
        testAdd();
        System.out.println("All tests passed!");
    }

    public static void testAdd() {
        int result = main.add(2, 3);
        if (result != 5) {
            throw new AssertionError("Expected 5 but got " + result);
        }
        System.out.println("testAdd: PASSED");
    }
}
