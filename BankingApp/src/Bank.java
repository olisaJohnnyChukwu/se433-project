import java.util.ArrayList;

public class Bank {
    public String name;
    public ArrayList<Customer> Customers;
    private ArrayList<Account> Accounts;
    public ArrayList<Loan> Loans;

    public Bank(String name) {
        this.name = name;
        this.Customers = new ArrayList<>();
        this.Loans = new ArrayList<>();
    }

//    public void addAccount(Account account){
//        Accounts.add(account);
//    }

    public void addCustomer(Customer customer) {
        Customers.add(customer);
    }

//    public void removeAccount(Account account){
//        if (Accounts.contains(account)) {
//            Accounts.remove(account);
//        }
//    }

    public void removeCustomer(Customer customer) {
        if (Customers.contains(customer)) {
            Customers.remove(customer);
        }
    }

    public Loan addNewLoan(Double loanAmount) {
        double rate;
        int yearsToCompletion;
        if (loanAmount < 10000) {
            rate = 0.10;
            yearsToCompletion = 3;
        } else if (loanAmount < 50000) {
            rate = 0.08;
            yearsToCompletion = 7;
        } else if (loanAmount < 200000) {
            rate = 0.05;
            yearsToCompletion = 7;
        } else if (loanAmount < 900000) {
            rate = 0.035;
            yearsToCompletion = 10;
        }
        else {
            System.out.println("Loan amount too high.");
            return null;
        }
        Integer ID = Loans.size();
        Loan newLoan = new Loan(loanAmount, rate, ID, yearsToCompletion);
        Loans.add(newLoan);
        return newLoan;
    }

}
