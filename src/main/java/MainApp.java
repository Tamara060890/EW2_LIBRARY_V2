
import java.util.Scanner;

public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    // 🏠 Main Menu
    public static void showMainMenu() {
        boolean running = true;

        System.out.println("\n");
        System.out.println("   📚 Welcome to INTEC Library! 📚  ");
        System.out.println("    =============================");
        System.out.println("\n");

        while (running) {
            printMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    showLibrarySelfService();
                    break;
                case 2:
                    showLibraryManagementSystem();
                    break;
                case 3:
                    System.out.println("\n👋 Bedankt voor het gebruik van het Library System!");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("🏠 MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("📖 1. Library Self Service");
        System.out.println("🛠️ 2. Library Management System");
        System.out.println("🚪 3. Exit");
        System.out.println("YOUR CHOICE:");
    }

    // 📖 Library Self Service Menu
    public static void showLibrarySelfService() {
        boolean inSelfService = true;

        while (inSelfService) {
            System.out.println("\n");
            System.out.println("📖 LIBRARY SELF SERVICE");
            System.out.println("=".repeat(40));
            System.out.println("🔍 1. Search for a Book");
            System.out.println("📚 2. View Borrowed Books");
            System.out.println("📥 3. Borrow a Book");
            System.out.println("📤 4. Return a Book");
            System.out.println("🔙 5. Back to Main Menu");
            System.out.println("YOUR CHOICE:");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    showSearchBookSelfService();
                    break;
                case 2:
                    viewBorrowedBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    inSelfService = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // 🔍 Search for a Book (Self Service)
    public static void showSearchBookSelfService() {
        boolean inSearch = true;

        while (inSearch) {
            System.out.println("\n");
            System.out.println("🔍 SEARCH FOR A BOOK (Self Service)");
            System.out.println("=".repeat(40));
            System.out.println("🔎 1. By Title");
            System.out.println("🖋️ 2. By Author");
            System.out.println("🧾 3. By ISBN");
            System.out.println("🔙 4. Back to Library Self Service");
            System.out.println("YOUR CHOICE:");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    searchByAuthor();
                    break;
                case 3:
                    searchByISBN();
                    break;
                case 4:
                    inSearch = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // 🛠️ Library Management System Menu
    public static void showLibraryManagementSystem() {
        boolean inManagement = true;

        while (inManagement) {
            System.out.println("\n");
            System.out.println("🛠️ LIBRARY MANAGEMENT SYSTEM (Employee Service)");
            System.out.println("=".repeat(40));
            System.out.println("📚 1. Book Management");
            System.out.println("👥 2. Member Management");
            System.out.println("🔙 3. Back to Main Menu");
            System.out.println("YOUR CHOICE:");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    showBookManagement();
                    break;
                case 2:
                    showMemberManagement();
                    break;
                case 3:
                    inManagement = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // 📚 Book Management Menu
    public static void showBookManagement() {
        boolean inBookManagement = true;

        while (inBookManagement) {
            System.out.println("\n");
            System.out.println("📚 BOOK MANAGEMENT (Employee Service)");
            System.out.println("=".repeat(40));
            System.out.println("➕ 1. Add a Book");
            System.out.println("❌ 2. Delete a Book");
            System.out.println("📝 3. Edit a Book");
            System.out.println("📤 4. Upload Books via CSV");
            System.out.println("🔍 5. Search Book");
            System.out.println("🔙 6. Back to Library Management System");
            System.out.println("YOUR CHOICE:");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    editBook();
                    break;
                case 4:
                    uploadBooksCSV();
                    break;
                case 5:
                    showSearchBookEmployee();
                    break;
                case 6:
                    inBookManagement = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // 🔍 Search for a Book (Employee Service)
    public static void showSearchBookEmployee() {
        boolean inEmployeeSearch = true;

        while (inEmployeeSearch) {
            System.out.println("\n");
            System.out.println("🔍 SEARCH FOR A BOOK (Employee Service)");
            System.out.println("=".repeat(40));
            System.out.println("🔎 1. By Title");
            System.out.println("🖋️ 2. By Author");
            System.out.println("🧾 3. By ISBN");
            System.out.println("🏷️ 4. By Intec ID");
            System.out.println("🔙 5. Back to Book Management");
            System.out.println("YOUR CHOICE:");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    searchByAuthor();
                    break;
                case 3:
                    searchByISBN();
                    break;
                case 4:
                    searchByIntecID();
                    break;
                case 5:
                    inEmployeeSearch = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // 👥 Member Management Menu
    public static void showMemberManagement() {
        boolean inMemberManagement = true;

        while (inMemberManagement) {
            System.out.println("\n");
            System.out.println("👥 MEMBER MANAGEMENT (Employee Service)");
            System.out.println("=".repeat(40));
            System.out.println("➕ 1. Add Member");
            System.out.println("❌ 2. Delete Member");
            System.out.println("📝 3. Edit Member Details");
            System.out.println("👤 4. View Member Profile");
            System.out.println("🔙 5. Back to Library Management System");
            System.out.println("YOUR CHOICE:");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    deleteMember();
                    break;
                case 3:
                    editMemberDetails();
                    break;
                case 4:
                    viewMemberProfile();
                    break;
                case 5:
                    inMemberManagement = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // Helper method voor input validatie
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Ongeldig getal
        }
    }

    // Placeholder methods voor functionaliteiten
    private static void viewBorrowedBooks() {
        System.out.println("\n📚 Weergeven van geleende boeken...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void borrowBook() {
        System.out.println("\n📥 Boek lenen...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void returnBook() {
        System.out.println("\n📤 Boek terugbrengen...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void searchByTitle() {
        System.out.print("\n🔎 Voer de titel in: ");
        String title = scanner.nextLine();
        System.out.println("Zoeken naar boek met titel: " + title);
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void searchByAuthor() {
        System.out.print("\n🖋️ Voer de auteur in: ");
        String author = scanner.nextLine();
        System.out.println("Zoeken naar boek van auteur: " + author);
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void searchByISBN() {
        System.out.print("\n🧾 Voer het ISBN in: ");
        String isbn = scanner.nextLine();
        System.out.println("Zoeken naar boek met ISBN: " + isbn);
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void searchByIntecID() {
        System.out.print("\n🏷️ Voer het Intec ID in: ");
        String intecId = scanner.nextLine();
        System.out.println("Zoeken naar boek met Intec ID: " + intecId);
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void addBook() {
        System.out.println("\n➕ Boek toevoegen...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void deleteBook() {
        System.out.println("\n❌ Boek verwijderen...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void editBook() {
        System.out.println("\n📝 Boek bewerken...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void uploadBooksCSV() {
        System.out.println("\n📤 Boeken uploaden via CSV...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void addMember() {
        System.out.println("\n➕ Lid toevoegen...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void deleteMember() {
        System.out.println("\n❌ Lid verwijderen...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void editMemberDetails() {
        System.out.println("\n📝 Lidgegevens bewerken...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void viewMemberProfile() {
        System.out.println("\n👤 Lidprofiel bekijken...");
        System.out.println("Deze functionaliteit wordt nog geïmplementeerd.");
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }
}


    /*private static void booksMenu() {
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
