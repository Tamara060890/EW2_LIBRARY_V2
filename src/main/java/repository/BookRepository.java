package repository;

//REPOSITORY (book): This is the interface where you define the METHODS you want to use to search books
//You use the interface to disconnect your code from the implementation

import model.Book;
import java.util.List;

public interface BookRepository {
    void addBook(Book book);
    boolean deleteBook(String intecID);
    void updateBook(Book updatedBook);
    Book searchBookTitle(String title);
    List<Book> searchBookAuthor(String author);
    Book searchBookISBN(String isbn);
    Book searchBookIntecID(String intecID);
    List<Book> getAllBooks();
    void loadBooksFromFile(String fileName);

}

