package model;

import java.time.LocalDate;
import java.util.Objects;


public class Loan {
    private Long id;
    private Book book;  // Object references
    private Member member;  // Object references
    private LocalDate loanDate; // when the loan starts
    private LocalDate dueDate; // when the book should be returned
    private LocalDate returnDate; // null while not returned
    private LoanStatus status; // ACTIVE, RETURNED, OVERDUE, LOST (matches  enum)

    public Loan() {

    }

    public Loan(Book book, Member member, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        if (returnDate != null) {
            this.status = LoanStatus.RETURNED;
        } else {
            this.status = LoanStatus.ACTIVE;
        }
    }

    //Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {  //Optional
        this.id = id;
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
        this.loanDate = loanDate; // <- fixed case
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


    //Methods

    // Mark this loan as returned now.
    public void returnBook() {
        LocalDate now = LocalDate.now();
        this.returnDate = now;
       // this.status = LoanStatus.RETURNED;
    }

        //True if the book has been returned
    public boolean isReturned() {
        return returnDate != null;
    }

    //True if today (or the given date) is past due and the book is not returned.

    public boolean isOverdue() {
        LocalDate today = LocalDate.now();
        if (returnDate != null) {
            return false;  // already returned
        }
        if (dueDate == null) {
            return false; // no due date set -> not considered overdue
        }
        return today.isAfter(dueDate);
    }


    @Override
    public String toString() {
        return "Loan{" +
                "book=" + (book != null ? book.getTitle() : "null") + //Ik wacht op the getters in de book class
                ", member=" + (member != null ? member.getName() : "null") + //Ik wacht op the getters in de book class
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }




}