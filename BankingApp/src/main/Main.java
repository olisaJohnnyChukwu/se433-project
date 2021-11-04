package main;

import account.SavingsAccount;
import bank.*;
import account.*;
import transaction.*;
import loan.*;
import customer.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Bank bank=new Bank("UE");
		String accountNumber=bank.generateAccountNumber();
		System.out.println(accountNumber);

		Account olisa=new SavingsAccount(accountNumber,5000.0);
		olisa.creditAmount(700);
		olisa.debitAmount(800);




		String accountNumber2=bank.generateAccountNumber();
		System.out.println(accountNumber2);
		Account bryan=new SavingsAccount(accountNumber2,8000.0);

		olisa.transferAmount(bryan,700);

		System.out.println(bryan.getBalance());

		System.out.println();

		System.out.println(olisa.printBankStatement());



		Bank MainBank = new Bank("MainBank");
		Customer cust1 = new Customer("John Doe", "123 abc Street", "123-456-7891", "Jdoe@gmail.com");
		cust1.applyForLoan(MainBank, 500000.00);
		for (Loan l: MainBank.Loans) {
			System.out.printf("Loan" + l.LoanID + " EMI: $%,.2f\n", l.getEMI());
		}
		cust1.payLoan(1000.00, 0);
		for (Loan l: MainBank.Loans) {
			System.out.printf("Loan" + l.LoanID + " $%,.2f\n", l.Amount);
		}
		cust1.payLoan(1000.00, 1);



		//Account  Account= new SavingsAccount(accountNumber, balance, transactions);

	}

}