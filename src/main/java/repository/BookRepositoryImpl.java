// ==================== BookRepositoryImpl.java ====================
package repository;

import model.Book;
import model.BookType;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BookRepositoryImpl implements BookRepository {
    private final Map<String, Book> bookInventory = new HashMap<>();
    private final String csvFileName = "books_inventory.csv";
    private int nextIdCounter = 1; // Track next available ID

    // Constructor - laadt automatisch data bij instantiatie (net als Member)
    public BookRepositoryImpl() {
        //loadFromCSV();
    }

    @Override
    public void addBook(Book book) {
        bookInventory.put(book.getIntecID(), book);
        updateCounterFromId(book.getIntecID());
        saveToCSV(); // Automatisch opslaan na elke wijziging
    }

    @Override
    public boolean deleteBook(String intecID) {
        boolean removed = bookInventory.remove(intecID) != null;
        if (removed) {
            saveToCSV(); // Automatisch opslaan na elke wijziging
        }
        return removed;
    }

    @Override
    public void updateBook(Book updatedBook) {
        bookInventory.put(updatedBook.getIntecID(), updatedBook);
        saveToCSV(); // Automatisch opslaan na elke wijziging
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
    public List<Book> getAllBooks() {
        return bookInventory.values().stream()
                .sorted(Comparator.comparing(Book::getIntecID))
                .collect(Collectors.toList());

    }

    @Override
    public void loadBooksFromFile(String fileName) {
        throw new UnsupportedOperationException("CSV import is managed in Business Logic Class: BookService.");
    }

    // Method to get next available ID (for BookService)
    public int getNextIdCounter() {
        return nextIdCounter++;
    }

    // Update counter when new ID is used
    private void updateCounterFromId(String intecID) {
        if (intecID.startsWith("INTEC")) {
            try {
                int idNumber = Integer.parseInt(intecID.substring(5));
                if (idNumber >= nextIdCounter) {
                    nextIdCounter = idNumber + 1;
                }
            } catch (NumberFormatException e) {
                // Ignore malformed IDs
            }
        }
    }

    // ================= CSV (precies zoals Member) =================
//    private void loadFromCSV() {
//        try (InputStream is = getClass().getClassLoader().getResourceAsStream(csvFileName)) {
//            if (is == null) {
//                System.out.println("❌ CSV bestand niet gevonden: " + csvFileName);
//                return;
//            }
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String line;
//            boolean firstLine = true;
//            int maxIdNumber = 0; // Track highest ID to set counter
//
//            while ((line = br.readLine()) != null) {
//                if (firstLine) {
//                    firstLine = false;
//                    continue;
//                } // skip header
//
//                String[] parts = line.split(",");
//                if (parts.length != 7) continue;
//
//                try {
//                    String intecID = parts[0].trim();
//                    String title = parts[1].trim();
//                    String author = parts[2].trim();
//                    int publicationYear = Integer.parseInt(parts[3].trim());
//                    String isbn = parts[4].trim();
//                    int availableCopies = Integer.parseInt(parts[5].trim());
//                    BookType bookType = BookType.valueOf(parts[6].trim().toUpperCase());
//
//                    Book book = new Book(intecID, bookType, title, author, publicationYear, isbn, availableCopies);
//                    bookInventory.put(intecID, book);
//
//                    // Extract number from IntecID to update counter
//                    if (intecID.startsWith("INTEC")) {
//                        try {
//                            int idNumber = Integer.parseInt(intecID.substring(5));
//                            maxIdNumber = Math.max(maxIdNumber, idNumber);
//                        } catch (NumberFormatException e) {
//                            // Ignore malformed IDs
//                        }
//                    }
//                } catch (Exception e) {
//                    System.out.println("⚠️ Fout bij verwerken regel: " + line + " - " + e.getMessage());
//                }
//            }
//
//            // Set counter to next available number
//            nextIdCounter = maxIdNumber + 1;
//
//            System.out.println("✅ Books geladen uit CSV.");
//        } catch (IOException e) {
//            System.out.println("❌ Fout bij lezen CSV: " + e.getMessage());
//        }
//    }

    private void saveToCSV() {
        // Sla terug naar target/resources bij runtime kan lastig zijn, daarom pad in project gebruiken
        File file = new File("src/main/resources/" + csvFileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("intecID,title,author,publicationYear,isbn,availableCopies,bookType\n");
            for (Book book : bookInventory.values()) {
                bw.write(String.format("%s,%s,%s,%d,%s,%d,%s\n",
                        book.getIntecID(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublicationYear(),
                        book.getIsbn(),
                        book.getAvailableCopies(),
                        book.getBookType().name()));
            }
        } catch (IOException e) {
            System.out.println("❌ Fout bij schrijven CSV: " + e.getMessage());
        }
    }
}

