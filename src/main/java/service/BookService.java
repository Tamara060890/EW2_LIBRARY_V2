package service;

// SERVICE (book): This class is responsible for the business logic, this is where you decide what will happen when you want to add, edit, search,...
// This class uses the BookRepository to save or retrieve data from

import model.Book;
import model.BookType;
import repository.BookRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class BookService {
    private final BookRepository bookRepository;
    private int counter = 1;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private String generateIntecID() {
        return String.format("INTEC%03d", counter++);
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

}



