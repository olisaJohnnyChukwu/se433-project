package customer;
import loan.*;
import transaction.TransactionException;

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

    public void payLoan(double payment, int LoanID, Account account) throws TransactionException {
        for (Loan l : Loans) {
            if (l.getLoanID() == LoanID) {
                if (Accounts.contains(account) && account.getBalance() >= payment) {
                    l.loanPayment(payment);
                    account.debitAmount(payment);
                }
                else {
                    throw new IllegalArgumentException("Insufficient funds. Please choose different account");
                }
            }
            else {
                throw new IllegalArgumentException("LoanID not found, payment not processed");
            }
        }
    }

  public void createAccount(Account account) {
        Accounts.add(account);
    }

    public void applyForLoan(Bank bank, Double loanAmount) {
        Loan l = bank.addNewLoan(loanAmount);
        Loans.add(l);
        bank.addCustomer(this);
    }

    public ArrayList<Account> getAccounts() {return Accounts;}

    public ArrayList<Loan> getLoans() {return Loans;}
}
