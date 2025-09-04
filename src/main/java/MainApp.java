
import java.time.format.TextStyle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import model.*;
import repository.*;
import service.LoanService;


// import your.package.service.BookService;
import model.Member;
import repository.MemberRepository;
import service.BookService;

// TODO: MemberService Imports

// TODO: LoanService Imports
import service.MemberService;
// import your.package.repository.impl.BookRepositoryImpl;

public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);

    // Service instanties - één keer aanmaken bij opstarten
    private static BookService bookService;
    private static MemberService memberService;  // Voor later
    private static LoanService loanService;




    public static void main(String[] args) {

    //Initialize all services with dependencies

        //Eerst services klaarzetten, zodat alles beschikbaar is.
        //Dan CSV inladen, zodat je zeker weet dat bookService klaar is.
        //Dan optioneel tonen van inventory, wat logisch volgt op het inladen.
        //Tot slot het menu, zodat de gebruiker verder kan werken.

        // 1. Initialize Services
        initializeServices();


        // Repository en service initialiseren
        MemberRepository memberRepo = new repository.MemberRepositoryImpl();
        memberService = new service.MemberService(memberRepo);

        // 3. Automatisch inladen van books_inventory.csv bij opstart
        bookService.loadBooksFromFile("books_inventory.csv");
        
        //4. Start menu
        showMainMenu();
    }

    // Initialize all services with dependencies
  
    private static void initializeServices() {
        // BookRepository instance
        BookRepository bookRepository = new BookRepositoryImpl();
        bookService = new BookService(bookRepository);

        // MemberRepository + MemberService
        MemberRepository memberRepository = new MemberRepositoryImpl();
        memberService = new MemberService(memberRepository);

        // Loan
        LoanRepository loanRepository = new LoanRepositoryImpl();
        loanService = new LoanService(loanRepository, bookRepository, memberRepository);
    }

    // Authentication methode to enter Library Self Service
    private static boolean authenticateMember() {
        System.out.print("🔑 Enter your email to login: ");
        String email = scanner.nextLine();

        try {
            Member member = memberService.searchMemberByEmail(email); // controleert of de email in CSV staat
            System.out.println("✅ Welcome, " + member.getName() + " (ID: " + member.getMemberId() + ")!");
            return true; // login succesvol
        } catch (MemberService.MemberNotFoundException e) {
            System.out.println("❌ Email not found. Try again.");
            return false; // login mislukt
        }
    }

    // Authentication method to enter Library Management
    private static boolean authenticateManager() {
        System.out.print("🔐 Add login code to access Library Management: ");
        String password = scanner.nextLine();
        return password.equals("admin123"); // Hard coded password for demo purposes, must be implemented more secure in the next quality phase.
    }



    // 🏠 Main Menu
    public static void showMainMenu() {
        boolean running = true;

        System.out.println("\n");
        System.out.println("📚 Welcome to INTEC Library! 📚  ");
        System.out.println("=".repeat(40));
        //System.out.println("\n");

        while (running) {
            printMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    boolean loggedIn = false;
                    while (!loggedIn) {
                        loggedIn = authenticateMember(); // vraagt om email tot het correct is
                    }
                    showLibrarySelfService(); // laat Self Service-menu zien na succesvolle login
                    break;
                case 2:
                    if (authenticateManager()) {
                    showLibraryManagementSystem();
                } else {
                    System.out.println("❌ Invalid login code. Access refused.");
                }
                break;

                case 3:
                    System.out.println("\n👋 Thank you for visiting the Intec Library!");
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
        System.out.println("🚪 0. Exit");
        System.out.println("");
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
            System.out.println("🔙 0. Back to Main Menu");
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
                case 0:
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
            System.out.println("🔙 0. Back to Library Self Service");
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
                case 0:
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
            System.out.println("🔙 0. Back to Main Menu");
            System.out.println("");
            System.out.println("YOUR CHOICE:");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    showBookManagement();
                    break;
                case 2:
                    showMemberManagement();
                    break;
                case 0:
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
            System.out.println("🔍 1. Search Book");
            System.out.println("➕ 2. Add a Book");
            System.out.println("❌ 3. Delete a Book");
            System.out.println("📝 4. Update a Book");
            System.out.println("📤 5. Upload Books via CSV");
            System.out.println("🔍 6. Show all Books");
            System.out.println("🔍 7. Show Book Statistics");
            System.out.println("📚 8. Loans for a Member");
            System.out.println("🔙 0. Back to Library Management System");
            System.out.println("");
            System.out.println("YOUR CHOICE:");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    showSearchBookEmployee();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    uploadBooksCSV();
                    break;
                case 6:
                    getAllBooks();
                    break;
                case 7:
                    inBookManagement = false;
                    break;
                case 8 :
                    showMemberLoansMenu();

                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // LOAN MENU

    private static void showMemberLoansMenu() {
        boolean inLoans = true;
        while (inLoans) {
            System.out.println("\n📚 MEMBER LOANS");
            System.out.println("=".repeat(40));
            System.out.println("1) Borrow a Book for Member");
            System.out.println("2) Return a Loan by ID");
            System.out.println("3) List Loans of Member");
            System.out.println("4) Back");
            System.out.println("YOUR CHOICE:");

            int c = getIntInput();
            switch (c) {
                case 1 :
                    borrowBook();        // reuses existing method
                case 2 :
                    returnBook();        // reuses existing method
                case 3 :
                    listLoansByMember(); // new method below
                case 4 :
                    inLoans = false;
                default :
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
    private static void listLoansByMember() {
        System.out.print("\nMember ID: ");
        try {
            Long memberId = Long.parseLong(scanner.nextLine().trim());
            List<Loan> list = loanService.getLoansByMember(memberId);
            if (list == null || list.isEmpty()) {
                System.out.println("No loans for this member.");
                return;
            }
            System.out.println("Loans:");
            for (Loan l : list) {
                System.out.println(" - " + l);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid Member ID.");
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
            System.out.println("🏷️ 3. By Type");
            System.out.println("🧾 4. By ISBN");
            System.out.println("🏷️ 5. By Intec ID");
            System.out.println("🔙 0. Back to Book Management");
            System.out.println("");
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
                    //searchByBookType();
                    break;
                case 4:
                    searchByISBN();
                    break;
                case 5:
                    searchByIntecID();
                    break;
                case 0:
                    inEmployeeSearch = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }


    // 👥 Member Management Menu
    private static void showMemberManagement() {
        boolean inMemberManagement = true;
        while (inMemberManagement) {
            System.out.println("\n👥 MEMBER MANAGEMENT (Employee Service)");
            System.out.println("=".repeat(40));
            System.out.println("➕ 1. Add Member");
            System.out.println("❌ 2. Delete Member");
            System.out.println("📝 3. Edit Member Details");
            System.out.println("👤 4. View Member Profile");
            System.out.println("📜 5. Show All Members");
            System.out.println("🔍 6. Search Member by Email");
            System.out.println("🔙 0. Back to Library Management System");
            System.out.println("");
            System.out.println("YOUR CHOICE:");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> deleteMember();
                case 3 -> editMemberDetails();
                case 4 -> viewMemberProfile();
                case 5 -> showAllMembers();
                case 6 -> searchMemberByEmail();
                case 0 -> inMemberManagement = false;
                default -> System.out.println("❌ Invalid choice. Please try again.");
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
    private static void searchByAuthor() {
        System.out.print("\n🖋️ ENTER AUTHOR: ");
        String author = scanner.nextLine();

        try {
            List<Book> books = bookService.searchBookAuthor(author);
            displaySearchResults(books, "author: " + author);
        } catch (Exception e) {
            System.out.println("❌ Error searching: " + e.getMessage());
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static void searchByBookType() {
        bookService.displayBookTypes(); // Show all types

        System.out.print("Enter BookType reference number (1-8): ");
        int typeRef = getIntInput();

        if (typeRef >= 1 && typeRef <= 8) {
            List<Book> books = bookService.searchBooksByTypeReference(typeRef);
            displaySearchResults(books, "BookType reference " + typeRef);
        } else {
            System.out.println("❌ Invalid BookType reference. Must be 1-8.");
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
           /* displaySearchResults(books, "auteur: " + author);
        } catch (Exception e) {
            System.out.println("❌ Fout bij zoeken: " + e.getMessage());
        }

        System.out.println("Druk op Enter om verder te gaan...");
=======
            displaySearchResults(books, "author: " + author);
        } catch (Exception e) {
            System.out.println("❌ Error searching: " + e.getMessage());
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static void searchByBookType() {
        bookService.displayBookTypes(); // Toon alle types

        System.out.print("Enter BookType reference number (1-8): ");
        int typeRef = getIntInput();

        if (typeRef >= 1 && typeRef <= 8) {
            List<Book> books = bookService.searchBooksByTypeReference(typeRef);
            displaySearchResults(books, "BookType reference " + typeRef);
        } else {
            System.out.println("❌ Invalid BookType reference. Must be 1-8.");
        }

        System.out.println("Press enter to continue...");

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
        System.out.print("\n🏷️ ENTER INTEC ID: ");
        String intecId = scanner.nextLine();

        Book foundBook = bookService.searchBookIntecID(intecId);
        if (foundBook != null) {
            System.out.println("📘 Book found: " + foundBook);
        } else {
            System.out.println("❌ No books found with this Intec ID.");
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static void showBookStatistics() {
        bookService.showBookStatistics();
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    // Helper method to show search results
    private static void displaySearchResults(List<Book> books, String searchCriteria) {
        if (books.isEmpty()) {
            System.out.println("❌ No books found for " + searchCriteria);
        } else {
            System.out.println("📚 Books found for " + searchCriteria + ":");
            System.out.println("=".repeat(50));
            for (Book book : books) {
                System.out.println(book);
                System.out.println("-----------------------------");
            }
        }
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
        System.out.print("Booktype: \n" +
                "    1\uFE0F⃣ TUTORIAL(1),           // Practical guides and how-to books\n" +
                "    2\uFE0F⃣ REFERENCE(2),          // Reference works and documentation\n" +
                "    3\uFE0F⃣ CONCEPTUAL(3),         // Theory and algorithms\n" +
                "    4\uFE0F⃣ PROJECT_BASED(4),      // Books based on projects\n" +
                "    5\uFE0F⃣ CAREER_SOFT_SKILLS(5), // Career development and soft skills\n" +
                "    6\uFE0F⃣ TRENDS_FUTURE(6),      // Technology and future vision\n" +
                "    7\uFE0F⃣ LANGUAGE_SPECIFIC(7),  // Books per programming language\n" +
                "    8\uFE0F⃣ FRAMEWORK_TOOL(8);      // Books about tools and frameworks\n" +
                "    YOUR CHOICE: ");
        BookType type = null;
        int typeNumber = getIntInput();
        try {
            type = BookType.fromNumber(typeNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Ongeldig boektype. Probeer opnieuw.");
            return;
        }

        Book newbook = bookService.addBook(type, title, author, year, isbn, copies);

        System.out.println("✅ NEW BOOK!");
        System.out.println("\uD83C\uDD94 Book ID: " + newbook.getIntecID());
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
        System.out.print("📤 Enter file name (books_inventory2.csv): ");
        String fileName = scanner.nextLine();
        bookService.loadBooksFromFile(fileName);
        System.out.println("✅ CSV data added to Inventory.\n");


        // Check uploaded books
        System.out.print("Do you want to review the inventory after upload? (Yes/No): ");
        String antwoord = scanner.nextLine();
        if (antwoord.equalsIgnoreCase("Yes")) {
            getAllBooks();
        }

        scanner.nextLine();
    }

    private static void getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("📭 No books found in inventory.");
        } else {
            System.out.println("\n📚 All books in inventory (" + allBooks.size() + " copies)");
            for (Book book : allBooks) {
                System.out.println(book);
                System.out.println("-----------------------------");
            }
        }

    }


    // MEMBER MANAGEMENT METHODS
    private static void addMember() {
        System.out.println("\n➕ Lid toevoegen...");
        System.out.print("Naam: "); String name = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Telefoonnummer: "); String phone = scanner.nextLine();

        // Maak een nieuw lid aan
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNumber(phone);
        member.setMembershipDate(LocalDate.now());

        try {
            // Lid toevoegen via service
            Member savedMember = memberService.addMember(member);

            // Toon bevestiging met het gegenereerde membershipNumber
            System.out.println("✅ Lid toegevoegd: " + savedMember.getName());
            System.out.println("Membership Number: " + savedMember.getMembershipNumber());
            System.out.println("Member ID: " + savedMember.getMemberId());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Fout: " + e.getMessage());
        }
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void deleteMember() {
        System.out.print("\nVoer Member ID in om te verwijderen: ");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            // Eerst lid ophalen om de gegevens te tonen
            Member member = memberService.findMemberById(id);

            // Toon lid info
            System.out.println("⚠️ Je staat op het punt het volgende lid te verwijderen:");
            System.out.println("ID: " + member.getMemberId());
            System.out.println("Naam: " + member.getName());
            System.out.println("Email: " + member.getEmail());
            System.out.println("Telefoon: " + member.getPhoneNumber());
            System.out.println("Membership Number: " + member.getMembershipNumber());
            System.out.println("Membership Date: " + member.getMembershipDate());

            // Bevestiging vragen
            System.out.print("Weet je zeker dat je dit lid wilt verwijderen? (ja/nee): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("ja")) {
                memberService.removeMember(id);
                System.out.println("✅ Lid verwijderd: " + member.getName());
            } else {
                System.out.println("❌ Verwijderen geannuleerd.");
            }
        } catch (MemberService.MemberNotFoundException e) {
            System.out.println("❌ Fout: " + e.getMessage());
        }

        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void editMemberDetails() {
        System.out.print("\nVoer Member ID in om te bewerken: ");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            Member member = memberService.findMemberById(id);

            System.out.print("Nieuwe naam (" + member.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isBlank()) member.setName(name);

            System.out.print("Nieuw telefoonnummer (" + member.getPhoneNumber() + "): ");
            String phone = scanner.nextLine();
            if (!phone.isBlank()) member.setPhoneNumber(phone);

            System.out.print("Nieuw email (" + member.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) member.setEmail(email);

            // Opslaan via updateMember
            memberService.updateMember(member);

            System.out.println("✅ Lidgegevens bijgewerkt: " + member.getName());
        } catch (MemberService.MemberNotFoundException e) {
            System.out.println("❌ Fout: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Ongeldige invoer: " + e.getMessage());
        }

        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void viewMemberProfile() {
        System.out.print("\nVoer Member ID in om profiel te bekijken: ");
        Long id = Long.parseLong(scanner.nextLine());
        try {
            Member member = memberService.findMemberById(id);
            System.out.println("Lidprofiel:\n" +
                    "ID: " + member.getMemberId() + "\n" +
                    "Naam: " + member.getName() + "\n" +
                    "Email: " + member.getEmail() + "\n" +
                    "Telefoon: " + member.getPhoneNumber() + "\n" +
                    "Membership Number: " + member.getMembershipNumber() + "\n" +
                    "Membership Date: " + member.getMembershipDate());
        } catch (MemberService.MemberNotFoundException e) {
            System.out.println("❌ Fout: " + e.getMessage());
        }
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void showAllMembers() {
        List<Member> members = memberService.showAllMembers();

        // Sorteer op memberId oplopend
        members.sort((m1, m2) -> m1.getMemberId().compareTo(m2.getMemberId()));

        System.out.println("\n📜 Alle leden (gesorteerd op ID):");
        System.out.println("ID | Naam | Email | Telefoon | Membership Number");
        System.out.println("---------------------------------------------------");
        for (Member m : members) {
            System.out.println(
                    m.getMemberId() + " | " +
                            m.getName() + " | " +
                            m.getEmail() + " | " +
                            m.getPhoneNumber() + " | " +
                            m.getMembershipNumber()
            );
        }
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }

    private static void searchMemberByEmail() {
        System.out.print("\nVoer e-mail in om te zoeken: ");
        String email = scanner.nextLine();
        try {
            Member member = memberService.searchMemberByEmail(email);
            System.out.println("Gevonden lid:\n" +
                    "ID: " + member.getMemberId() + "\n" +
                    "Naam: " + member.getName() + "\n" +
                    "Email: " + member.getEmail() + "\n" +
                    "Telefoon: " + member.getPhoneNumber());
        } catch (MemberService.MemberNotFoundException e) {
            System.out.println("❌ Fout: " + e.getMessage());
        }
        System.out.println("Druk op Enter om verder te gaan...");
        scanner.nextLine();
    }
}
