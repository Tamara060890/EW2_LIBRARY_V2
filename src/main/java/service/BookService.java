package service;

// SERVICE (book): This class is responsible for the business logic, this is where you decide what will happen when you want to add or search,...

import model.Book;
import model.BookType;
import repository.BookRepository;

import java.util.List;

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
        Book book = new Book(intecID, bookType, title, author, publicationYear,isbn, availableCopies);
        bookRepository.addBook(book);
        return book;
    }

    public boolean deleteBook(String intecID) {
        return bookRepository.deleteBook(intecID);
    }

    public boolean editBook(String intecID, Book updatedBook) {
        return bookRepository.editBook(intecID, updatedBook);
    }

    public Book searchBookTitle(String title) {
        return bookRepository.searchByTitle(title);
    }

    public Book searchBookAuthor(String author) {
        return bookRepository.searchByAuthor(author);
    }

    public Book searchBookISBN(String isbn) {
        return bookRepository.searchByISBN(isbn);
    }

    public Book searchBookIntecID(String intecID) {
        return bookRepository.searchByIntecID(intecID);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

}
