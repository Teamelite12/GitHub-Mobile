import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            greet(args[0]);
        } else {
            runInteractiveMode();
        }
    }

    private static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    private static void runInteractiveMode() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            System.out.println("Welcome to the Sample Application!");
            System.out.println("==================================");

            while (running) {
                printMenu();
                System.out.print("Enter your choice: ");
                
                String input = scanner.nextLine().trim();
                
                switch (input) {
                    case "1":
                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                        greet(name);
                        break;
                    case "2":
                        System.out.println("Current time: " + LocalDateTime.now());
                        break;
                    case "3":
                        System.out.println("Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                System.out.println();
            }
        }
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Personalized Greeting");
        System.out.println("2. Display Current Time");
        System.out.println("3. Exit");
    }
}
