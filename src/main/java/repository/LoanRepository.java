package repository;

import model.Loan;
import java.util.List;

public interface LoanRepository {

    // Create or update a loan. Returns the stored instance (with loanId filled).
    Loan save(Loan loan);

    // Find a loan by loanId. Returns null if not found.
    Loan findById(Long loanId);

    // All loans for a given memberId.
    List<Loan> findByMemberId(Long memberId);

    // All loans currently active (not returned).
    List<Loan> findActiveLoans();

    // All loans that are overdue (today > dueDate and not returned).
    List<Loan> findOverdueLoans();

    // All loans (for listing/debug).
    List<Loan> findAll();

    // Delete by loanId. Returns true if something was removed.
    boolean delete(Long loanId);
}

