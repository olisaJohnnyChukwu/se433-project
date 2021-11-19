package bank;
import java.util.*;

import account.*;
import customer.*;
import loan.*;

public class Bank {
    private String name;
    private ArrayList<Customer> Customers;
    private ArrayList<Account> Accounts;
    private ArrayList<Loan> Loans;

    public Bank(String name) {
        this.name = name;
        this.Customers = new ArrayList<>();
        this.Loans = new ArrayList<>();
        this.Accounts = new ArrayList<>();
    }

    public void addAccount(Account account){
        Accounts.add(account);
        }

    public void addCustomer(Customer customer) {
        Customers.add(customer);
    }

  public void removeAccount(Account account){
        if (Accounts.contains(account)) {
            Accounts.remove(account);
        }
    }

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
            throw new IllegalArgumentException("Loan amount too high.");
        }

        Integer ID = Loans.size();
        Loan newLoan = new Loan(loanAmount, rate, ID, yearsToCompletion);
        Loans.add(newLoan);
        return newLoan;
    }


    public String generateAccountNumber(){
  
    Random rand = new Random();
    String accountNo = "";
    for (int i = 0; i < 14; i++)
    {
        int n = rand.nextInt(10);
        accountNo += Integer.toString(n);
    }
   
    return accountNo;
    
    }

    public ArrayList<Customer> getCustomers() {return Customers;}

    public ArrayList<Account> getAccounts() {return Accounts;}

    public ArrayList<Loan> getLoans() {return Loans;}

}
