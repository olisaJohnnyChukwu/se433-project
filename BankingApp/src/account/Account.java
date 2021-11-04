package account;
import java.util.*;
import transaction.*;
import customer.*;
import bank.*;


public abstract class Account {
    private String accountNumber;
    private double balance;
    private ArrayList<Transaction> transactions=new ArrayList<>();
    private double interestRate;
    private  double interestEarned;
    private  double minimumBalance;
    private  int maxTransactionNumber;
    public static final double time = 0.0833;
    private  double averageDailyBalance;
    private  int countTransactions;
    
    //private ArrayList<Customer> customers;


    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
       
       // customers = new ArrayList<>();
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public int getMaxTransactionNumber() {
        return maxTransactionNumber;
    }

    public void setMaxTransactionNumber(int maxTransactionNumber) {
        this.maxTransactionNumber = maxTransactionNumber;
    }

    public void debitAmount(double debit) {
        if (debit > 0) {
            if (balance >= debit) {
                Transaction transaction =new DepositTransaction(debit);
                this.balance=transaction.apply(balance);
                transactions.add(transaction);
                
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Credit amount must be greater than zero.");
        }
    }

    /*public void withdrawAmount(double withdrawalAmount) {
        if (this.balance < withdrawalAmount) {
            System.out.println("You have insufficient funds.");
        } else {
            if (withdrawalAmount <= 0) {
                System.out.println("The withdrawal amount must be greater than zero.");
            } else {
                this.balance -= withdrawalAmount;
                transactions.add(new Transaction(withdrawalAmount));
                countTransactions++;
            }

        }
    }*/

    public void transferAmount(Account toAccount, double transferAmount) {
        if (this.getBalance() > 0 && transferAmount > 0) {
           // toAccount.setBalance(toAccount.balance += transferAmount);
            //this.balance -= transferAmount;
           // transactions.add(new Transaction(transferAmount));
           Transaction transaction=new TransferTransaction(transferAmount, toAccount,this);
           this.balance=transaction.apply(balance);
           transactions.add(transaction);
            countTransactions++;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void Recieve(Account fromAccount,double debit){
        
        Transaction transaction =new RecieveTransaction(debit, fromAccount);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
    }
    public void creditAmount(double credit) {
        if (credit > 0) {
            //this.balance += credit;
            Transaction transaction =new WithdrawTransaction(credit);
            this.balance=transaction.apply(balance);
            transactions.add(transaction);
            countTransactions++;
        } else {
            System.out.println("Credit amount must be greater than zero.");
        }
    }

    
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double calculateInterestEarned() {
        interestEarned = averageDailyBalance * interestRate * time;
        return interestEarned;
    }

    public void addInterest() {
        balance += interestEarned;
    }

    public String printBankStatement(){
        String statement="";
        for(Transaction t:transactions){
            statement+=t.printTransaction()+"\n";

        }
        return statement;        
    }
    /*public void deposit(double deposit) {
        if (deposit <= 0) {
            System.out.println("The deposit amount must be greater than zero.");
        } else {
            balance += deposit;
            transactions.add(new Transaction(deposit));
            countTransactions++;

        }
    }*/

  /*  public void addCustomer(Customer customer) {
        if (customers.size() < 1) {
            this.customer = customer;
            customers.add(customer);
        } else {
            System.out.println("This account already has a customer.");
        }
    }

    public void removeCustomer(Customer customer) {
        if (customers.isEmpty() == false) {
            customers.remove(customer);
        } else {
            System.out.println("This account does not have a customer");
        }
    }*/



}
