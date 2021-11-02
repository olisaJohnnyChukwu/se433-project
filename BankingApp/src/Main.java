public class Main {
    public static void main(String[] args) {
        Bank MainBank = new Bank("MainBank");
        Customer cust1 = new Customer("John Doe", "123 abc Street", "123-456-7891", "Jdoe@gmail.com");
        cust1.applyForLoan(MainBank, 500000.00);
        for (Loan l: MainBank.Loans) {
            System.out.printf("Loan" + l.LoanID + " EMI: $%,.2f\n", l.getEMI());
        }
        cust1.payLoan(1000.00, 0);
        for (Loan l: MainBank.Loans) {
            System.out.printf("Loan" + l.LoanID + " $%,.2f\n", l.Amount);
        }
        cust1.payLoan(1000.00, 1);

    }
}
