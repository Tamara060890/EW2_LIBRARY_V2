/*
import java.util.Scanner;
public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=================================");
        System.out.println("   📚 Welcome to the Library! 📚  ");
        System.out.println("=================================");

        while (running) {
            printMainMenu();
            int choice = readChoice();

            switch (choice) {
                case 1 -> booksMenu();
                case 2 -> membersMenu();
                case 3 -> loansMenu();
                case 0 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Books");
        System.out.println("2. Members");
        System.out.println("3. Loans");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    private static void booksMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Books Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Show All Books");
            System.out.println("5. Upload file");
            System.out.println("0. Back");
            System.out.print("Your choice: ");

            int choice = readChoice();
            switch (choice) {
                case 1 -> System.out.println("Adding a book...");
                case 2 -> System.out.println("Removing a book...");
                case 3 -> System.out.println("Searching for a book...");
                case 4 -> System.out.println("Showing all books...");
                case 5 -> System.out.println("Showing all books...");
                    repo.loadBooksFromFile("books_inventory.csv");

                    System.out.println("Aantal boeken geladen: " + repo.getAllBooks().size());
                }

                case 0 -> back = true;
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void membersMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Members Menu ---");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Search Member");
            System.out.println("4. Show All Members");
            System.out.println("0. Back");
            System.out.print("Your choice: ");

            int choice = readChoice();
            switch (choice) {
                case 1 -> System.out.println("Adding a member...");
                case 2 -> System.out.println("Removing a member...");
                case 3 -> System.out.println("Searching for a member...");
                case 4 -> System.out.println("Showing all members...");
                case 0 -> back = true;
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void loansMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Loans Menu ---");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Show All Borrowed Books");
            System.out.println("0. Back");
            System.out.print("Your choice: ");

            int choice = readChoice();
            switch (choice) {
                case 1 -> System.out.println("Borrowing a book...");
                case 2 -> System.out.println("Returning a book...");
                case 3 -> System.out.println("Showing all borrowed books...");
                case 0 -> back = true;
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static int readChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
*/
