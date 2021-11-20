package account;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private  int countTransactions;
    private String openingDate;

    
    private ArrayList<Customer> customers;


    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;

       customers = new ArrayList<>();
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

    
    public Transaction debitAmount(double debit) throws TransactionException {
    	checkBalance(debit);
        validateAmount(debit);
        Transaction transaction =new WithdrawTransaction(debit);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
        
        
        return transaction;


    }
    
    /**
     * 
     *
     * @param  	Account toAccount   Reciever account
     * @return    Transaction object
     */
    public Transaction transferAmount(Account toAccount, double transferAmount) throws TransactionException {
        checkBalance(transferAmount);//check if the balance of the account is  sufficient for the transacation
        validateAmount(transferAmount);//check transaction is more than zero and  and less than 5000;
        
        //create a new transfer transaction with the user account as an argument
        Transaction transaction=new TransferTransaction(transferAmount, toAccount,this);
        //execute the transaction and update the balance
        this.balance=transaction.apply(balance);
        //add to the list of transaction
        transactions.add(transaction);
        //update the count 
        countTransactions++;
        
        return transaction;
        
    }

    public Transaction Recieve(Account fromAccount,double debit){
        //
        Transaction transaction =new RecieveTransaction(debit, fromAccount);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
        
        return transaction;
    }


    public Transaction creditAmount(double credit) throws TransactionException {
        validateAmount(credit);
        Transaction transaction =new DepositTransaction(credit);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
        countTransactions++;
        return transaction;

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
        interestEarned = balance * interestRate * time;
        return interestEarned;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public double addInterest() {
        balance += interestEarned;
        return balance;
    }

    public String printBankStatement(){
        String statement="";
        for(Transaction t:transactions){
            statement+=t.printTransaction()+"\n";

        }
        
        return statement.length()!=0 ?statement:"You have no previous Transactions";
          
    }
   

   
    
    
   


    public void validateAmount(Double amount) throws TransactionException{
        if(amount<=0.0){
            throw  new TransactionException("Transaction amount has to be  Than 0");
        }

        if(amount>=5000.0){
            throw  new TransactionException("Transaction amount less Tham  5000");
        }
            

    }

    public void checkBalance(Double amount) throws TransactionException{
        if(amount>balance){
            throw  new TransactionException("Insufficient funds");
        }


    }


}
