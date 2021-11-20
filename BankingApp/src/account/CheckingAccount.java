package account;

import java.util.Random;

public class CheckingAccount extends Account {
    private double minimumBalance;
    private double interestRate;
    private double interestEarned;
    private double time = 0.0833;
    private double balance;
    private String debitCardNumber;
    private int debitCardLength;
    private int debitCardPin;
    private int debitCardPinLength;

    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        this.balance = balance;

    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String generateDebitCardNumber(){
        Random random = new Random();
        String debitCardNumber = "";
        debitCardLength = 12;
        for (int i = 0; i<debitCardLength; i++){
            int n = random.nextInt(10);
            debitCardNumber += Integer.toString(n);
        }
        return debitCardNumber;
    }

    public String generateDebitCardPin(){
        Random random = new Random();
        String debitCardPin = "";
        debitCardPinLength = 4;
        for (int i = 0; i<debitCardPinLength; i++){
            int n = random.nextInt(10);
            debitCardPin += Integer.toString(n);
        }
        return debitCardPin;
    }

    @Override
    public double calculateInterestEarned() {
        interestEarned = balance * interestRate * time;
        return interestEarned;
    }
}
