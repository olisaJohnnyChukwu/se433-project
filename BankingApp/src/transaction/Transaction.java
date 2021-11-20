package transaction;

public abstract class Transaction {
    protected int  transactionId ;
    protected  Double amount;//The amount of the transaction
    protected  TransactionType transactionType;//type of transaction
    protected  static int ID=1000;
    protected  Double balanceAfterTransaction;//current balance after transaction
    



   public  String printTransaction(){
	   //concat the class variables to form transaction statement;
	   return transactionType+" "+amount+" id "+transactionId+" balance "+balanceAfterTransaction;
   };
   
   
   //this abstract method executes the different variations of transactions in the sub class
    public abstract Double apply(Double balance);


   


	public int getTransactionId() {
		return transactionId;
		
	}

	

	
	

	
	
    
   



    
}
