package transaction;

public class DepositTransaction  extends Transaction{

    public  DepositTransaction(Double amount){
        this.transactionType=TransactionType.DEPOSIT;
        this.amount=amount;
      //set the transaction Id to the current number of the global variable
        this.transactionId=Transaction.ID;
        //increment the static variable ID variable to ensure that the id numbers are unique
        Transaction.ID++;
        
    }

    
    /**
     * Add the amount to deposit to the  balance of account
     * set the balance after variable  to the new balance. 
     * and return the new balance.
     * 
     * @param  balance -the  current account balance
     * @return newBalance   -the new balance after the transaction 
     */
    @Override
    public Double apply(Double balance) {
    
        Double newBalance=balance+amount;
        this.balanceAfterTransaction=newBalance;
        System.out.println(printTransaction());
        return newBalance;
    }

   
    
}
