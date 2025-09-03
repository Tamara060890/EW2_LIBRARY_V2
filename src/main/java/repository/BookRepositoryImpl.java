package repository;

//REPOSITORY (book): This is the actual logic to save books in a list, database or file.

import model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookRepositoryImpl implements BookRepository {
    private final Map<String, Book> bookInventory = new HashMap<>();

    /*private int counter = 1;

    private String generateIntecID() {
        return String.format("INTEC%03d", counter++);
    }*/

    @Override
    public void addBook(Book book) {
        bookInventory.put(book.getIntecID(), book);
    }

    @Override
    public boolean deleteBook(String intecID) {
        return bookInventory.remove(intecID) != null;
    }

    @Override
    public void updateBook(Book updatedBook) {
            bookInventory.put(updatedBook.getIntecID(), updatedBook);
    }

    @Override
    public Book searchBookTitle(String title) {
        return bookInventory.values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<Book> searchBookAuthor(String author) {
        return bookInventory.values().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }


    @Override
    public Book searchBookISBN(String isbn) {
        return bookInventory.values().stream()
                .filter(book -> book.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book searchBookIntecID(String intecID) {
        return bookInventory.get(intecID);
    }

    @Override
    public void loadBooksFromFile(String fileName) {
        throw new UnsupportedOperationException("CSV import is managed in Business Logic Class: BookService.");
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookInventory.values());
    }

}
