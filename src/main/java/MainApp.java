import java.time.format.TextStyle;
import java.util.Scanner;
import java.util.List;

// TODO: BookService Imports
import model.Book;
import model.BookType;
import service.BookService;
import repository.BookRepository;
import repository.BookRepositoryImpl;

// TODO: MemberService Imports

// TODO: LoanService Imports


public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);

    // Service instanties - één keer aanmaken bij opstarten
    private static BookService bookService;
    // private static MemberService memberService;  // Voor later
    // private static LoanService loanService;      // Voor later


    public static void main(String[] args) {
        // Initialize Services
        initializeServices();

        //Start Menu
        showMainMenu();
    }

    //Initialize all services with dependencies
    private static void initializeServices() {
        // BookRepository instance
        BookRepository bookRepository = new BookRepositoryImpl();

        // BookService instance with dependency injection
        bookService = new BookService(bookRepository);

        // MemberRepository memberRepository = new MemberRepositoryImpl();
        // memberService = new MemberService(memberRepository);

        // LoanRepository loanRepository = new LoanRepositoryImpl();
        // loanService = new LoanService(loanRepository);
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
        System.out.println("🛠️ 2. Library Management");
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
                    //searchByAuthor();
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
            System.out.println("🔍 6. Show all Books");
            System.out.println("🔙 7. Back to Library Management System");
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
                    updateBook();
                    break;
                case 4:
                    uploadBooksCSV();
                    break;
                case 5:
                    showSearchBookEmployee();
                    break;
                case 6:
                    getAllBooks();
                    break;
                case 7:
                    inBookManagement = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }


    // 🔍 SEARCH BOOK MENU (Employee Service)
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
                    //searchByAuthor();
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

    // 👥 MEMBER MANAGEMENT MENU
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

    // Helper method for input validation
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


    // SEARCH BOOK METHODS
    private static void searchByTitle() {
        System.out.print("\n🔎 ENTER TITLE: ");
        String title = scanner.nextLine();
        System.out.println("Search for books with title " + title);

        Book foundBook = bookService.searchBookTitle(title);
        if (foundBook != null) {
            System.out.println("📘 Book found: " + foundBook);
        } else {
            System.out.println("❌ No books found with this title.");
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    /*private static void searchByAuthor() {
        System.out.print("\n🖋️ ENTER AUTHOR: ");
        String author = scanner.nextLine();

        try {
            List<Book> books = bookService.searchBookAuthor(author);
            displaySearchResults(books, "auteur: " + author);
        } catch (Exception e) {
            System.out.println("❌ Fout bij zoeken: " + e.getMessage());
        }

        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }*/

    private static void searchByISBN() {
        System.out.print("\n🧾 ENTER ISBN: ");
        String isbn = scanner.nextLine();

        Book foundBook = bookService.searchBookISBN(isbn);
        if (foundBook != null) {
            System.out.println("📘 Book found: " + foundBook);
        } else {
            System.out.println("❌ No books found with this ISBN.");
        }

        System.out.println("Press enter to continue...");
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


    // BOOK MANAGEMENT METHODS
    private static void addBook() {
        System.out.println("\n➕ ADD BOOK");

        System.out.print("\uD83D\uDCD8 Book title: ");
        String title = scanner.nextLine();

        System.out.print("\uFE0F Author: ");
        String author = scanner.nextLine();

        System.out.print("\uD83D\uDCC5 Year of publication: ");
        int year = getIntInput();

        System.out.print("\uD83D\uDD22 ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("\uD83D\uDCDA Available copies: ");
        int copies = getIntInput();
        scanner.nextLine();

        System.out.print("Booktype: " +
                "    TUTORIAL(1),           // Practical guides and how-to books\n" +
                "    REFERENCE(2),          // Reference works and documentation\n" +
                "    CONCEPTUAL(3),         // Theory and algorithms\n" +
                "    PROJECT_BASED(4),      // Books based on projects\n" +
                "    CAREER_SOFT_SKILLS(5), // Career development and soft skills\n" +
                "    TRENDS_FUTURE(6),      // Technology and future vision\n" +
                "    LANGUAGE_SPECIFIC(7),  // Books per programming language\n" +
                "    FRAMEWORK_TOOL(8);      // Books about tools and frameworks");
        BookType type = BookType.valueOf(scanner.nextLine().toUpperCase());

        Book newbook = bookService.addBook(type, title, author, year, isbn, copies);

        System.out.println("✅ NEW BOOK!");
        System.out.println("Book ID: " + newbook.getIntecID());

        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static void deleteBook() {
        System.out.println("\n❌ DELETE BOOK");
        try {
            System.out.print("Enter Intec ID: ");
            String IntecID = scanner.nextLine();

            boolean deleted = bookService.deleteBook(IntecID);

            if (deleted) {
                System.out.println("✅ BOOK DELETED");
            } else {
                System.out.println("❌ Book not found.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static void updateBook() {
        System.out.println("\n📝 UPDATE BOOK");
        try {
            System.out.print("Enter Intec ID: ");
            String IntecID = scanner.nextLine();

            // Eerst huidige boek ophalen
            Book existingBook = bookService.searchBookIntecID(IntecID);

            if (existingBook == null) {
                System.out.println("❌ Book not found.");
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                return;
            }

            System.out.println("Current data:");
            System.out.println("Book title: " + existingBook.getTitle());
            System.out.println("Author: " + existingBook.getAuthor());
            System.out.println("Year: " + existingBook.getPublicationYear());
            System.out.println("ISBN: " + existingBook.getIsbn());
            System.out.println("Copies: " + existingBook.getAvailableCopies());
            System.out.println("Type: " + existingBook.getBookType());

            System.out.print("New Book title (press enter to keep): ");
            String newTitle = scanner.nextLine();
            if (newTitle.trim().isEmpty()) {
                newTitle = existingBook.getTitle();
            }

            System.out.print("New author (press enter to keep): ");
            String newAuthor = scanner.nextLine();
            if (newAuthor.trim().isEmpty()) {
                newAuthor = existingBook.getAuthor();
            }

            System.out.print("New year (press enter to keep): ");
            String yearInput = scanner.nextLine();
            int newYear = existingBook.getPublicationYear();
            if (!yearInput.trim().isEmpty()) {
                try {
                    newYear = Integer.parseInt(yearInput);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid year, keeping original.");
                }
            }

            System.out.print("New ISBN (press enter to keep): ");
            String newIsbn = scanner.nextLine();
            if (newIsbn.trim().isEmpty()) {
                newIsbn = existingBook.getIsbn();
            }

            System.out.print("New available copies (press enter to keep): ");
            String copiesInput = scanner.nextLine();
            int newCopies = existingBook.getAvailableCopies();
            if (!copiesInput.trim().isEmpty()) {
                try {
                    newCopies = Integer.parseInt(copiesInput);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid number, keeping original.");
                }
            }

            // Create updated book object
            Book updatedBook = new Book(
                    existingBook.getIntecID(),
                    existingBook.getBookType(),
                    newTitle,
                    newAuthor,
                    newYear,
                    newIsbn,
                    newCopies
            );

            // BookService aanroepen om boek te updaten
            bookService.updateBook(updatedBook);

            System.out.println("✅ Book successfully updated!");

        } catch (Exception e) {
            System.out.println("❌ Error updating book: " + e.getMessage());
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static void uploadBooksCSV() {
        System.out.println("\n📤 Add books via CSV...");
        System.out.print("📤 Enter file name (books_inventory.csv): ");
        String fileName = scanner.nextLine();
        bookService.loadBooksFromFile(fileName);
        System.out.println("✅ CSV data added to Inventory.\n");

        scanner.nextLine();
    }

    private static void getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("📭 No books found in inventory.");
        } else {
            System.out.println("\n📚 All books in inventory:");
            for (Book book : allBooks) {
                System.out.println(book);
                System.out.println("-----------------------------");
            }
        }

    }


    // MEMBER MANAGEMENT METHODS
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