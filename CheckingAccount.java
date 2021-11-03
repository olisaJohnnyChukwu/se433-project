import java.util.ArrayList;
import java.util.Date;

public class CheckingAccount extends Account {
    private static final double minimumBalance = 250.00;
    private static final double interestRate = 0.001;
    private double interestEarned;
    private double averageDailyBalance;
    private Date openingDate;

    public CheckingAccount(String accountNumber, double balance, ArrayList<Transaction> transactions) {
        super(accountNumber, balance, transactions);
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
    @Override
    public double calculateInterestEarned() {
        interestEarned = averageDailyBalance * interestRate * time;
        return interestEarned;
    }

}
