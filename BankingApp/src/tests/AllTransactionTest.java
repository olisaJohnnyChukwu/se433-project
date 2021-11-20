package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import account.Account;
import account.CheckingAccount;
import account.SavingsAccount;
import bank.Bank;
import customer.Customer;
import transaction.Transaction;
import transaction.TransactionException;
import transaction.TransactionType;

public class AllTransactionTest {
		Account olisa;
		Account laura;
		
		
		
			
			
	
	
			@BeforeEach
			public void setup() {
				 olisa=new SavingsAccount("000",2000.0);
				 laura=new CheckingAccount("001",5000.0);
		        
		    }
			
			
			@Test
	 		public void  deposit() throws TransactionException{
		 	
			double depositAmount=1000.0;	
			//credit account the deposit amount
		 	olisa.creditAmount(depositAmount);
		 	laura.creditAmount(depositAmount);
		 	//expected balance for olisa and laura account after transaction
	        Double olisaNewBalance=3000.0;
	        Double lauraNewBalance=6000.0;
	        
	        //check the balance is as expected
	        Assertions.assertEquals(lauraNewBalance, laura.getBalance());
	        Assertions.assertEquals(olisaNewBalance, olisa.getBalance());
	        
	    }
			
			
		
			@Test
		    public void invalidAmount() throws TransactionException{
	 		 
				
				//test for invalid transactions were transaction amount is 0 
	 			int zeroNotEligible=0;
	 			//transaction amount is more than the allowed limit per transaction 50000
	 			Double maxTransactionAmount=5000.0;
	 			//negative transaction
	 			Double negativeNumbersnotAllowed=-5.0;
	 			//check if the transaction exception are thrown
	 			Assertions.assertThrows(TransactionException.class, ()->{olisa.creditAmount(zeroNotEligible);});
	 			Assertions.assertThrows(TransactionException.class, ()->{olisa.creditAmount(maxTransactionAmount);});
	 			Assertions.assertThrows(TransactionException.class, ()->{olisa.creditAmount(negativeNumbersnotAllowed);});
		      
		       
		    }
			
			
			 @Test
			    public void validAmountDeposit(){
			    	//1 is the min allowed transaction
			    	int minAllowedTransaction=1;
			    	//4999.9 is the max allowed transaction
			    	Double maxAllowedTransaction=4999.9;
			    	
			    	//check if the the transaction exeption is not thrown
			    	Assertions.assertDoesNotThrow(()->{laura.creditAmount(minAllowedTransaction);});
			    	Assertions.assertDoesNotThrow(()->{laura.creditAmount(maxAllowedTransaction);});
			    	
			    }
			 
			 
			 
			 
			   @Test
			    public void PrintStatementDeposit() throws TransactionException {
			    	
			    	Double transactionAmount=1000.0;
			    	//credit account with transaction amount
			    	Transaction transaction=olisa.creditAmount(transactionAmount);
			    	 //construct the expected statement with the transaction  class and account class  variables
			    	String statement=TransactionType.DEPOSIT+" "+transactionAmount+" id "+transaction.getTransactionId()+" balance "+olisa.getBalance();
			    	//confirm the strings are the same
			    	Assertions.assertEquals(transaction.printTransaction(),statement);
				      
			    }
			   
			   
			   @Test
			    public void  recieve() throws TransactionException{
				   	
			        Double transactionAmount=1000.0;
			        //execute a receive transaction with olisa being the sender 
			        laura.Recieve(olisa, transactionAmount);
			        //expected balance
			        Double lauraBalance=6000.0;
			        //confirm equal
			        Assertions.assertEquals(lauraBalance,laura.getBalance(),0);
			        
			        

			   }
			   
			   
			   
			   
			   @Test
			    public void printTransationRecieve() {
			    
			   
			   double transactionAmount=1000.0;
			   //execute a receive transaction with olisa being the sender 
			   Transaction transaction=laura.Recieve(olisa, transactionAmount);
			   //construct the expected statement with the transaction  class and account class  variables	
			   String statement=TransactionType.RECIEVE+" "+transactionAmount+" id "+transaction.getTransactionId()+" balance "+laura.getBalance()+" sender "+olisa.getAccountNumber();
			   
			   //assert the statement and transaction are the same
			   Assertions.assertEquals(transaction.printTransaction(), statement);
			    	
			    }
			   
			   
			   
			   @Test
			    public void  transfer() throws TransactionException{
			    	
			    	double transactionAmount=1000.0;
			    	
			    	//execute a transfer transaction
			        olisa.transferAmount(laura, transactionAmount);
			        //expected balance of olisa account after the transaction
			        Double olisaBalance=1000.0;
			        //expected balance of laura account after the transaction
			        Double lauraBalance=6000.0;


			        Assertions.assertEquals(olisaBalance,olisa.getBalance(),0);
			        Assertions.assertEquals(lauraBalance,laura.getBalance(),0);
			    }
			    
			    
			   @Test
			    public void  invalidTransfer() throws TransactionException{
			   
				 // test a value that is expected to throw a transaction exception when the amount > balance
			    Double moreThanOlisaBalance=2050.0;
			    // test a value that is expected to throw a transaction exception when the amount equal to zero
			    Double zeroNotAllowed=0.0;
			    
			    //check that the exceptions are thrown
			    Assertions.assertThrows(TransactionException.class, ()->{olisa.transferAmount(laura, moreThanOlisaBalance);});
			    Assertions.assertThrows(TransactionException.class, ()->{olisa.transferAmount(laura, zeroNotAllowed);});
			    
			    }
			   
			   
			   
			   @Test
			    public void  validTransfer() throws TransactionException{
			    	 
					 //check if the account is allowed to transfer its entire balance
					 Double transferEntireBalance=olisa.getBalance();
					 //assert the transaction is not thrown
					 Assertions.assertDoesNotThrow(()->{olisa.transferAmount(laura, transferEntireBalance);});
			    	
			    
			    }
			   
			   
			   @Test
			    public void invalidAmountWithdrawal() throws TransactionException{
				 //test for invalid transactions were transaction amount is 0 
		 			int zeroNotEligible=0;
		 			//transaction amount is more than the allowed limit per transaction 50000
		 			Double maxTransactionAmount=5000.0;
		 			//negative transaction expected to throw an exception
		 			Double negativeNumbersnotAllowed=-5.0;
					
		 			//assert that errors are thrown
			       Assertions.assertThrows(TransactionException.class, ()->{laura.debitAmount(zeroNotEligible);});
			       Assertions.assertThrows(TransactionException.class, ()->{laura.debitAmount(maxTransactionAmount+1);});
			       Assertions.assertThrows(TransactionException.class, ()->{laura.debitAmount(maxTransactionAmount);});
			       //check that negative amounts aren't allowed 
			       Assertions.assertThrows(TransactionException.class, ()->{olisa.debitAmount(negativeNumbersnotAllowed);});
			    }


	
			   @Test
			    public void InsufficientBalanceBeforeWithdrawal() throws TransactionException{
			    	
					Double Amount=2500.0;
					//assert that if we withdraw an amount amount more than the balance
			        Assertions.assertThrows(TransactionException.class, ()->{olisa.debitAmount(Amount);});
			       
			       
			    }
			   
			   
			   @Test
				 public void printStatements() throws TransactionException {
			    	
				   	// the transaction amounts 
			    	Double amount=1000.0;
			    	Double amount1=500.0;
			    	//execute the transactions
					Transaction transaction1=olisa.debitAmount(amount);
					
					Transaction transaction2=olisa.creditAmount(amount1);
				
					Transaction transaction3=olisa.transferAmount(laura, amount1);
					
					
					//statement1 is the 1st expected line of the  printstatement()
					String statement1=TransactionType.WITHDRAW+" "+amount+" id "+transaction1.getTransactionId()+" balance "+1000.0+"\n";
					//statement2 is the 2st expected line of the  printstatement()
					String statement2=TransactionType.DEPOSIT+" "+amount1+" id "+transaction2.getTransactionId()+" balance "+1500.0+"\n";
					//statement2 is the 3rd expected line of the  printstatement()
					String statement3=TransactionType.TRANSFER+" "+amount1+" id "+transaction3.getTransactionId()+" balance "+1000.0+" sent to "+laura.getAccountNumber()+"\n";
					
					 //assert they are equal
					 Assertions.assertEquals(statement1+statement2+statement3, olisa.printBankStatement());
					 
				 }
			   
			   
			   @Test
				 public void noPrintStatements() throws TransactionException {
					 
					//assert an account with no transaction prints "You have no previous Transactions"
					 Assertions.assertEquals("You have no previous Transactions", olisa.printBankStatement());
					 
				 }

		
	 

}
