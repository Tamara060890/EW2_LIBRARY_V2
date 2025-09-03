package service;

import model.Book;
import model.Member;
import model.Loan;
import model.LoanStatus;

import repository.LoanRepository;
import repository.BookRepository;
import repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;


public class LoanService {

    // Repositories
    private final LoanRepository loanRepo;
    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;

    // Constructor
    public LoanService(LoanRepository loanRepo, BookRepository bookRepo, MemberRepository memberRepo) {
        this.loanRepo = loanRepo;
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    // Borrow with default duration (14 days)
    public Loan borrow(Long memberId, String intecID, String isbn) {
        return borrow(memberId, intecID, isbn, 14);
    }

    // Borrow with custom duration. IntecID has priority over ISBN.
    public Loan borrow(Long memberId, String intecID, String isbn, int days) {
        if (memberId == null)
            throw new IllegalArgumentException("memberId is required");
        if (days <= 0)
            throw new IllegalArgumentException("days must be > 0");

        // Resolve member (interface returns Optional)
        Member member = memberRepo.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalStateException("Member not found: " + memberId));

        // Resolve book: try IntecID first, then ISBN (compact Optional chaining)
        Book book = null;
        if (intecID != null && !intecID.isBlank()) {
            book = bookRepo.searchBookIntecID(intecID); // expect Book or null
        }
        if (book == null && isbn != null && !isbn.isBlank()) {
            book = bookRepo.searchBookISBN(isbn); // expect Book or null
        }
        if (book == null) {
            throw new IllegalStateException("Book not found (IntecID/ISBN)");
        }

        // Check stock
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No available copies for: " + book.getTitle());
        }
        // Build and persist loan
        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(days));
        loan.setReturnDate(null);
        loan.setStatus(LoanStatus.ACTIVE);

        Loan stored = loanRepo.save(loan);

        // Decrement inventory
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        return stored;
    }

    // Return a loan (close + give the copy back)
    public boolean returnLoan(Long loanId) {
        if (loanId == null) throw new IllegalArgumentException("loanId is required");

        Loan loan = loanRepo.findById(loanId);
        if (loan == null) throw new IllegalStateException("Loan not found: " + loanId);
        if (loan.getStatus() != LoanStatus.ACTIVE) return false;

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        Book book = loan.getBook();
        if (book != null) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            // bookRepo.save(book);
        }

        loanRepo.save(loan); // update persisted loan
        return true;
    }

    // Search methods

    public Loan getById(Long id) {
        return loanRepo.findById(id);
    }

    public List<Loan> getActiveLoans() {
        return loanRepo.findActiveLoans();
    }

    public List<Loan> getOverdueLoans() {
        return loanRepo.findOverdueLoans();
    }

    public List<Loan> getLoansByMember(Long memberId) {
        return loanRepo.findByMemberId(memberId);
    }

    public List<Loan> getAllLoans() {
        return loanRepo.findAll();
    }
}
