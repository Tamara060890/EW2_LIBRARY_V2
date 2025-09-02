package repository;

//REPOSITORY (book): This is the actual logic to save books in a list, database or file.

import model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository {
    private final Map<String, Book> bookInventory = new HashMap<>();

    @Override
    public void addBook(Book book) {
        bookInventory.put(book.getIntecID(), book);
    }

    @Override
    public boolean deleteBook(String intecID) {
        return false;
    }

    @Override
    public boolean editBook(String intecID, Book updatedBook) {
        return false;
    }

    @Override
    public Book searchByTitle(String title) {
        return null;
    }

    @Override
    public Book searchByAuthor(String author) {
        return null;
    }

    @Override
    public Book searchByISBN(String isbn) {
        return null;
    }

    @Override
    public Book searchByIntecID(String intecID) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.of();
    }
}
