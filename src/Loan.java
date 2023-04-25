import java.time.format.DateTimeFormatter;

public class Loan {

    public enum LoanStatus {
        ACTIVE,
        OVERDUE,
        RETURNED
    }

    private Member owner;

    private DateTimeFormatter returnnDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private LoanStatus loanStatus;

    public Loan(Member owner, DateTimeFormatter returnnDate, LoanStatus loanStatus){
        owner = this.owner;
        returnnDate = this.returnnDate;
        loanStatus = this.loanStatus;
    }

    public void RegisterLoan(){

    }
}
