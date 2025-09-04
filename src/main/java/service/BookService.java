// ==================== BookService.java ====================
package service;

import model.Book;
import model.BookType;
import repository.BookRepository;
import repository.BookRepositoryImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

/**
 * SERVICE (book): This class is responsible for the business logic,
 * this is where you decide what will happen when you want to add, edit, search,...
 * This class uses the BookRepository to save or retrieve data from
 */

public class BookService {
    private final BookRepositoryImpl bookRepository; // Use concrete type to access getNextIdCounter

    public BookService(BookRepository bookRepository) {
        this.bookRepository = (BookRepositoryImpl) bookRepository;
    }

    private String generateIntecID() {
        return String.format("INTEC%03d", bookRepository.getNextIdCounter());
    }

    public Book addBook(BookType bookType, String title, String author, int publicationYear, String isbn, int availableCopies) {
        String intecID = generateIntecID();
        Book book = new Book(intecID, bookType, title, author, publicationYear, isbn, availableCopies);
        bookRepository.addBook(book);
        System.out.println(book);
        System.out.println("✅ New book added successfully!");
        return book;
    }

    public boolean deleteBook(String intecID) {
        return bookRepository.deleteBook(intecID);
    }

    public void updateBook(Book updatedBook) {
        bookRepository.updateBook(updatedBook);
    }

    public Book searchBookTitle(String title) {
        return bookRepository.searchBookTitle(title);
    }

    public List<Book> searchBookAuthor(String author) {
        return bookRepository.searchBookAuthor(author);
    }

    public Book searchBookISBN(String isbn) {
        return bookRepository.searchBookISBN(isbn);
    }

    public Book searchBookIntecID(String intecID) {
        return bookRepository.searchBookIntecID(intecID);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    /**
     * Search books by BookType
     */
    public List<Book> searchBooksByType(BookType bookType) {
        if (bookType == null) {
            return new ArrayList<>();
        }

        return bookRepository.getAllBooks().stream()
                .filter(book -> book.getBookType() == bookType)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Search books by BookType reference number
     */
    public List<Book> searchBooksByTypeReference(int referenceNumber) {
        return bookRepository.getAllBooks().stream()
                .filter(book -> book.getBookType().getReferenceNumber() == referenceNumber)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get all available BookTypes with their reference numbers
     */
    public void displayBookTypes() {
        System.out.println("\n📚 Available Book Types:");
        System.out.println("=".repeat(40));
        for (BookType type : BookType.values()) {
            System.out.printf("(%d) %s%n", type.getReferenceNumber(), type.name());
        }
        System.out.println();
    }

    public void loadBooksFromFile(String fileName) {
        int uploadedCount = 0;

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                System.out.println("Bestand niet gevonden in resources: " + fileName);
                return;
            }

            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // skip header
                }

                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int publicationYear = Integer.parseInt(parts[2].trim());
                    String isbn = parts[3].trim();
                    int availableCopies = Integer.parseInt(parts[4].trim());
                    BookType bookType = BookType.valueOf(parts[5].trim().toUpperCase());

                    // Genereer intecID en voeg toe via BookService
                    String intecID = generateIntecID();
                    Book book = new Book(intecID, bookType, title, author, publicationYear, isbn, availableCopies);
                    bookRepository.addBook(book);
                    uploadedCount++;
                }
            }

            System.out.println("✅ " + uploadedCount + " books successfully added from: " + fileName);

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get books statistics by type
     */
    public void showBookStatistics() {
        List<Book> allBooks = getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("📊 No books in inventory to show statistics.");
            return;
        }

        System.out.println("\n📊 Book Statistics:");
        System.out.println("=".repeat(40));
        System.out.println("Total books: " + allBooks.size());

        // Group by BookType
        for (BookType type : BookType.values()) {
            long count = allBooks.stream()
                    .filter(book -> book.getBookType() == type)
                    .count();
            if (count > 0) {
                System.out.printf("(%d) %s: %d books%n",
                        type.getReferenceNumber(), type.name(), count);
            }
        }
        System.out.println();
    }
}


