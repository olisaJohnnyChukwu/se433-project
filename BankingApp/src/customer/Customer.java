package customer;
import loan.*;
import java.util.*;
import bank.*;
import account.*;

public class Customer {
    String Name;
    String Address;
    String PhoneNumber;
    String EmailAddress;
    ArrayList<Account> Accounts;
    ArrayList<Loan> Loans;

    public Customer(String name, String address, String phone, String email) {
        this.Name = name;
        this.Address = address;
        this.PhoneNumber = phone;
        this.EmailAddress = email;
        this.Loans = new ArrayList<>();
    }

    public void payLoan(double payment, int LoanID) {
        for (Loan l : Loans) {
            if (l.LoanID == LoanID) {
                l.Amount -= payment;
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
