public class main {
    private static final String APP_NAME = "GitHub Mobile";
    private static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        System.out.println("Welcome to " + APP_NAME + "!");
        System.out.println("Version: " + VERSION);
        System.out.println();
        
        displaySystemInfo();
    }

    private static void displaySystemInfo() {
        System.out.println("System Information:");
        System.out.println("  Java Version: " + System.getProperty("java.version"));
        System.out.println("  OS: " + System.getProperty("os.name"));
        System.out.println("  User: " + System.getProperty("user.name"));
    }
}
