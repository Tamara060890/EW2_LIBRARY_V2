package model;

import java.time.LocalDate;

public class Loan {

    private Long loanId;          // Unique identifier for this loan
    private Book book;            // The book being borrowed (Object reference)
    private Member member;        // The member who borrowed the book (Object reference)
    private LocalDate loanDate;   // When the loan starts
    private LocalDate dueDate;    // When the book should be returned
    private LocalDate returnDate; // Null while not returned
    private LoanStatus status;    // ACTIVE, RETURNED, OVERDUE, LOST

    public Loan() {
    }

    public Loan(Book book, Member member, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;

        // Keep status consistent with initial data
        if (returnDate != null) {
            this.status = LoanStatus.RETURNED;
        } else {
            this.status = LoanStatus.ACTIVE;
        }
    }

    // --- Getters & Setters ---
    public Long getLoanId() {
        return loanId;
    }
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        if (returnDate != null) {
            this.status = LoanStatus.RETURNED;
        }
    }

    public LoanStatus getStatus() {
        return status;
    }
    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    // --- Methods ---
    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.status = LoanStatus.RETURNED;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public boolean isOverdue() {
        if (returnDate != null) return false; // already returned
        if (dueDate == null) return false;    // no due date set
        return LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        String bookTitle = (book != null) ? book.getTitle() : "null";
        String memberName = (member != null) ? member.getName() : "null";

        return "\n📄 Loan Details:\n" +
                "🆔 Loan ID: " + loanId + "\n" +
                "📚 Book: " + bookTitle + "\n" +
                "👤 Member: " + memberName + "\n" +
                "📅 Loan Date: " + loanDate + "\n" +
                "📅 Due Date: " + dueDate + "\n" +
                "📅 Return Date: " + returnDate + "\n" +
                "📌 Status: " + status + "\n";
    }
}
