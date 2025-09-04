package model;


import java.util.HashMap;
import java.util.Map;

//MODEL (book): This is your data object or entity. Here you define the properties of a book.

public class Book {
    private final String intecID;    // bv INTEC0001
    private final BookType bookType;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private int availableCopies;

    public Book(String intecID, BookType bookType, String title, String author, int publicationYear, String isbn, int availableCopies) {
        this.intecID = intecID;
        this.bookType = bookType;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.availableCopies = availableCopies;
    }

    public String getIntecID() {
        return intecID;
    }

    public BookType getBookType() {
        return bookType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

//    @Override
//    public String toString() {
//        return "\n📘 Title: " + title +
//                "\n✍️ Author: " + author +
//                "\n📅 Publication Year: " + publicationYear +
//                "\n🔢 ISBN: " + isbn +
//                "\n📚 Available Copies: " + availableCopies +
//                "\n📖 Book Type: " + bookType +
//                "\n🆔 Intec ID: " + intecID;
//
//    }


    @Override
     public String toString() {
     return String.format("\uD83C\uDD94 ID: %s | \uD83D\uDCD8 Title: %s | \uD83C\uDD94 Author: %s | \uD83D\uDCC5 Year: %d | \uD83D\uDD22 ISBN: %s | \uD83D\uDCDA Copies: %d | \uD83D\uDCD6 Type: %s",
           intecID, title, author, publicationYear, isbn, availableCopies, bookType);
    }

}
