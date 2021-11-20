package account;

public class SavingsAccount extends Account{
    private double minimumBalance;
    private double interestRate;
    private double interestEarned;
    private double time = 0.0833;
    private double balance;
    private static int MAXTRANSACTIONNUMBER = 6;
    private static double ANNUALINTERESTRATE = 0.01;
    private double principleBalance;
    private double annualInterestEarned;



    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        this.balance = balance;
        setPrincipleBalance(balance);

    }

    @Override
    public double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setPrincipleBalance(double principleBalance) {
        this.principleBalance = principleBalance;
    }

    public double getPrincipleBalance() {
        return principleBalance;
    }

    public int getMaxTransactionNumber() {
        return MAXTRANSACTIONNUMBER;
    }

    public double calculateAnnualInterestEarned(){
        annualInterestEarned=principleBalance*ANNUALINTERESTRATE*1;
        return annualInterestEarned;
    }
    @Override
    public double calculateInterestEarned(){
        interestEarned = balance * interestRate * time;
        return interestEarned;
    }


}
