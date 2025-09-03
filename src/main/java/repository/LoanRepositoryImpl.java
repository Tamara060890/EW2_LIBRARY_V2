package repository;

import model.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanRepositoryImpl implements LoanRepository {

    private final List<Loan> loans = new ArrayList<>();

    // In-memory id generator for Loan entities (matches Long loanId)
    private Long nextLoanId = 1L;

    @Override
    public Loan save(Loan loan) {
        if (loan == null) return null;

        // Create
        if (loan.getLoanId() == null) {
            loan.setLoanId(nextLoanId++);
            loans.add(loan);
            return loan;
        }

        // Update
        for (int i = 0; i < loans.size(); i++) {
            Loan current = loans.get(i);
            if (loan.getLoanId().equals(current.getLoanId())) {
                loans.set(i, loan);
                return loan;
            }
        }

        // Fallback: id set but not found -> append
        loans.add(loan);
        return loan;
    }

    @Override
    public Loan findById(Long loanId) {
        if (loanId == null) return null;
        return loans.stream()
                .filter(l -> loanId.equals(l.getLoanId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Loan> findByMemberId(Long memberId) {
        if (memberId == null) return new ArrayList<>();
        return loans.stream()
                .filter(l -> l.getMember() != null
                        && l.getMember().getMemberId() != null
                        && memberId.equals(l.getMember().getMemberId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findActiveLoans() {
        return loans.stream()
                .filter(l -> !l.isReturned())
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findOverdueLoans() {
        return loans.stream()
                .filter(Loan::isOverdue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(loans); // defensive copy
    }

    @Override
    public boolean delete(Long loanId) {
        if (loanId == null) return false;
        return loans.removeIf(l -> loanId.equals(l.getLoanId()));
    }
}
