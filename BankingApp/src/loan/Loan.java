package loan;


public class Loan {
    private double Amount;
    private double Interest;
    private int LoanID;
    private int YearsTillCompletion;

    public Loan(Double amount, Double interest, Integer loanID, Integer years) {
        this.Amount = amount;
        this.Interest = interest;
        this.LoanID = loanID;
        this.YearsTillCompletion = years;
    }

    //https://www.investopedia.com/terms/e/equated_monthly_installment.asp
    public Double getEMI() {
        return (Amount + (Amount * YearsTillCompletion * Interest)) / (YearsTillCompletion * 12);
    }

    //https://homeguides.sfgate.com/calculate-prepayment-penalty-mortgage-7571.html
    public Double getPrepayment() {
        return ((Amount * Interest) / 12) * 6;
    }

    public void loanPayment(double payment) {
        Amount -= payment;
        YearsTillCompletion -= (1/12);
    }

    public double getAmount() { return Amount;}

    public  double getInterest() { return Interest;}

    public int getLoanID() { return LoanID;}
    
}
