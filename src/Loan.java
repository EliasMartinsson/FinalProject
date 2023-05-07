import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {

    public enum LoanStatus {
        ACTIVE,
        OVERDUE,
        RETURNED
    }

    String bookTitle;

    private Member owner;

    private LocalDate returnDate;

    private LoanStatus loanStatus;

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Loan(Member owner, LocalDate returnDate, LoanStatus loanStatus, String bookTitle){
        this.owner = owner;
        this.returnDate = returnDate;
        this.loanStatus = loanStatus;
        this.bookTitle = bookTitle;
    }

    public void print(){
        System.out.println("Return date: " +this.returnDate);
        System.out.println("Loan status: " + this.loanStatus);
    }

}
