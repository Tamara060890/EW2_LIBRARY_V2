package repository;

//REPOSITORY (book): This is the actual logic to save books in a list, database or file.

import model.Book;
import model.BookType;
import service.BookService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    public Book searchBookTitle(String title) {
        return null;
    }

    @Override
    public Book searchBookAuthor(String author) {
        return null;
    }

    @Override
    public Book searchBookISBN(String isbn) {
        return null;
    }

    @Override
    public Book searchBookIntecID(String intecID) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookInventory.values());
    }

    @Override
    public void loadBooksFromFile(String fileName) {

    }

}
