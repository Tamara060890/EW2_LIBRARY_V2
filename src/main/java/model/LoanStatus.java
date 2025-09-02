package model;


//Possible statuses for a Loan.
public enum LoanStatus {

    ACTIVE,     // The loan is ongoing
    RETURNED,   // The book has been returned
    OVERDUE,    // The loan is past due date
    LOST        // The book is lost


}
