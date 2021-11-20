package transaction;
import account.*;

public class RecieveTransaction extends Transaction{
	Account fromAccount; 


    public  RecieveTransaction(Double amount,Account fromAccount){
        this.transactionType=TransactionType.RECIEVE;
        this.amount=amount;
        //set the transaction Id to the current number of the global variable
        this.transactionId=Transaction.ID;
        this.fromAccount=fromAccount;
        
        //increment the static variable ID variable to ensure that the id numbers are unique
        Transaction.ID++;
        
    }

    /**
     * Add the amount received from the  The account balance of the receiver
     * and set the balanceAfterTransaction variable to the new balance
     * @param  balance -the  current account balance
     * @return newBalance   -the new balance
     */
    @Override
    public Double apply(Double balance) {
        // TODO Auto-generated method stub
        Double newBalance=balance+amount;
        this.balanceAfterTransaction=newBalance;
        return newBalance;
    }


    /*	
     * @param  
     * @return   return a concatenation of all the transaction variables
     */
    
    public  String printTransaction(){
    	
    	//call the printTransaction method from the super class ,
    	//and append account number of the sender 
    	return super.printTransaction()+" sender "+fromAccount.getAccountNumber();
                
    };
    
}
