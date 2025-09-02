package repository;
import model.Loan;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class LoanRepositoryImpl implements LoanRepository {

    private final List<Loan> loans = new ArrayList<>();

    //L for "loan"
    private Long nextLoanId = 1L;


    @Override
    public Loan save(Loan loan) {
       /* if (loan.getLoanId()Id() == null) {
            loan.setLoanId();Id(nextLoanId++);
            loans.add(loan);
            return loan;
        }*/
    }

    @Override
    public Loan findById(Long id) {
        return null;
    }

    @Override
    public List<Loan> findByMemberId(Long memberId) {
        return List.of();
    }

    @Override
    public List<Loan> findActiveLoans() {
        return List.of();
    }

    @Override
    public List<Loan> findOverdueLoans() {
        return List.of();
    }

    @Override
    public List<Loan> findAll() {
        return List.of();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
