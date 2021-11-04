package customer;
import loan.*;
import java.util.*;
import bank.*;
import account.*;

public class Customer {
    private String Name;
    private String Address;
    private String PhoneNumber;
    private String EmailAddress;
    private ArrayList<Account> Accounts;
    private ArrayList<Loan> Loans;

    public Customer(String name, String address, String phone, String email) {
        this.Name = name;
        this.Address = address;
        this.PhoneNumber = phone;
        this.EmailAddress = email;
        this.Loans = new ArrayList<>();
        this.Accounts = new ArrayList<>();
    }

    public void payLoan(double payment, int LoanID, Account account) {
        for (Loan l : Loans) {
            if (l.LoanID == LoanID) {
                if (Accounts.contains(account) && account.getBalance() >= payment) {
                    l.Amount -= payment;
                    l.YearsTillCompletion -= (1/12);
                }
                else {
                    System.out.println("Insufficient funds. Please choose different account");
                }
            }
            else {
                System.out.println("LoanID not found, payment not processed");
            }
        }
    }

  public void createAccount(Account account) {
        Accounts.add(account);
    }

    public void applyForLoan(Bank bank, Double loanAmount) {
        bank.addCustomer(this);
        Loan l = bank.addNewLoan(loanAmount);
        if (l != null) {
            Loans.add(l);
        }
    }
}