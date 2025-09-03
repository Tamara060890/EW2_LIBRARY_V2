/*package repository;
import model.Loan;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class LoanRepositoryImpl implements LoanRepository {

    private final List<Loan> loans = new ArrayList<>();

    //L for "loan"
    private Long nextLoanId = 1L;


    @Override
    public Loan save(Loan loan) {
        if (loan == null) return null;

        // New loan (no loanId yet) → assign id and add
        if (loan.getLoanId() == null) {
            loan.setLoanId(nextLoanId++);
            loans.add(loan);
            return loan;
        }

    }

        @Override
    public Loan findById(Long id) {
        if (id == null) return null;

        return loans.stream()
                .filter(l -> id.equals(l.getLoanId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Loan> findByMemberId(Long memberId) {
        if (memberId == null) return new ArrayList<>();

        return loans.stream()
                .filter(l -> l.getMember() != null
                        && l.getMember().getId() != null //ik wacht op the Mamber class
                        && memberId.equals(l.getMember().getId())) //ik wacht op the Mamber class
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
        // Defensive copy, so the caller cannot modify internal list
        return new ArrayList<>(loans);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) return false;
        return loans.removeIf(l -> id.equals(l.getLoanId()));
    }
}
 */