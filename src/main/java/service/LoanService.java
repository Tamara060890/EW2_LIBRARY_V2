//package service;
//
//import repository.BookRepository;
//import repository.LoanRepository;
//import repository.MemberRepository;
//
//public class LoanService {
//private LoanRepository loanrepository;
//private BookRepository bookRepository;
//private MemberRepository memberRepository;
//
//    public LoanService(LoanRepository loanrepository, BookRepository bookRepository, MemberRepository memberRepository) {
//        this.loanrepository = loanrepository;
//        this.bookRepository = bookRepository;
//        this.memberRepository = memberRepository;
//    }
////     +borrowBook(Long, Long): Loan
////        +returnBook(Long): Loan
////        +findLoanById(Long): Loan
////        +findLoansByMember(Long): List<Loan>
////        +getActiveLoans(): List<Loan>
////        +getOverdueLoans(): List<Loan>
////        +calculateFine(Loan): BigDecimal
//
//
//
////    public Loan borrowBook(Long memberId, Long bookId) {
////     // logica om boek uit te lenen aan lid
////    }
//
////    public Loan findLoanById(Long loanId) {
////    // Zoek de lening in de repository en geef ze terug
////    }
//
////    public List<Loan> findLoansByMember(Long memberId) {
////    // Zoek alle leningen van het lid met dit ID
////    }
//
////    //methode wordt gebruikt om te zien welke boeken nog zijn uitgeleend
////    public List<Loan> getActiveLoans() {
//    // Vraag de repository om alle leningen waarvan de returnDate nog niet is ingevuld
////    return loanRepository.findActiveLoans();
////    }
//
////    //methode om een lijst van leningen die te laat zijn te vragen
////    public List<Loan> getOverdueLoans() {
////        // Vraag de repository om alle leningen die over tijd zijn
////        return loanRepository.findOverdueLoans();
////    }
//
//}
