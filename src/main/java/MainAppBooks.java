import model.Book;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import service.BookService;

import java.util.List;
import java.util.Scanner;

public class MainAppBooks {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepositoryImpl();
        BookService bookService = new BookService(bookRepository);
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Loan Service");
            System.out.println("2. Book Service");
            System.out.println("3. Member Service");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> System.out.println("Loan Service is not yet implemented.");
                case 2 -> bookServiceMenu(bookService, scanner);
                case 3 -> System.out.println("Member Service is not yet implemented.");
                case 0 -> exit = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void bookServiceMenu(BookService bookService, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Book Service ---");
            System.out.println("1. Manage Books");
            System.out.println("2. Search Books");
            System.out.println("0. Exit Book Service");
            System.out.print("Your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> System.out.println("1");
                case 2 -> System.out.println("2");
                case 0 -> back = true;
                default -> System.out.println("Ongeldige keuze.");
            }
        }
    }


    private static void manageBooksMenu(BookRepositoryImpl repo, Scanner scanner) {
        System.out.println("\n--- Manage Books ---");
        System.out.println("1. Add Book");
        System.out.println("2. Delete Book");
        System.out.println("3. Update Book");
        System.out.println("4. Upload books via csv");
        System.out.print("Your choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> System.out.println("Add Book logic here...");
            case 2 -> System.out.println("Delete Book logic here...");
            case 3 -> System.out.println("Update Book logic here...");
            case 4 -> System.out.println("Update Upload logic here...");
            default -> System.out.println("Ongeldige keuze.");
        }
    }

    private static void searchBooksMenu(BookRepositoryImpl repo, Scanner scanner) {
        System.out.println("\n--- Search Books ---");
        System.out.println("1. By Title");
        System.out.println("2. By Author");
        System.out.println("3. By ISBN");
        System.out.println("4. By IntecID");
        System.out.print("Your choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> System.out.println("Search by title logic...");
            case 2 -> System.out.println("Search by author logic...");
            case 3 -> System.out.println("Search by ISBN logic...");
            case 4 -> System.out.println("Search by IntecID logic...");
            default -> System.out.println("Invalid choice.");
        }
    }



}
