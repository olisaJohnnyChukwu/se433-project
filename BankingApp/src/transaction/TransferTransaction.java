package transaction;

import account.*;


public class TransferTransaction extends Transaction{
    Account recieverAccount;//account of reciever
    Account fromAccount;//account of sender

    public  TransferTransaction (Double amount, Account recieverAccount, Account fromAccount){
        this.transactionType=TransactionType.TRANSFER;
        this.amount=amount;
        //set the transaction Id to the current number of the global variable
        this.transactionId=Transaction.ID;
        this.recieverAccount=recieverAccount;
        this.fromAccount=fromAccount;
        //increment the static variable ID variable to ensure that the id numbers are unique
        Transaction.ID++;
        
    }
    @Override
    public Double apply(Double balance) {
        // TODO Auto-generated method stub
    	//subtract the amount to transfer from the balance
        Double newBalance=balance-amount;
        //update the balance after transaction variable
        this.balanceAfterTransaction=newBalance;
        //create a new receive transaction with the receiver account
        recieverAccount.Recieve(fromAccount, amount);
        System.out.println(printTransaction());
        //return the new balance to be stored in the sender account.
        return newBalance;
    }
    
    public  String printTransaction(){
    	//call the super classes print transaction method and append the recievers account number
    	return super.printTransaction()+" sent to "+recieverAccount.getAccountNumber();
        
   };
}
