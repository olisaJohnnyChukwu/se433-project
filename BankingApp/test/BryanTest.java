import account.Account;
import account.CheckingAccount;
import account.SavingsAccount;
import bank.Bank;
import customer.Customer;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Bryan Morandi test class, testing: Bank, Customer, and Loan")
public class BryanTest {
    Bank testBank;
    Customer testCustomer1;
    Customer testCustomer2;
    Account savings;
    Account checking;


    @BeforeEach
    public void setup() {
        testBank = new Bank("Test Bank");

        savings = new SavingsAccount(testBank.generateAccountNumber(), 5000.00);
        testCustomer1 = new Customer("Cust1", "123 abc Street", "123-456-7891", "Cust1@gmail.com");
        testCustomer2 = new Customer("Cust2", "456 def Street", "987-654-3219", "Cust2@gmail.com");
        checking =  new CheckingAccount(testBank.generateAccountNumber(), 5000.00);
    }

    @Nested
    @DisplayName("Bank class tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class BankTests {
        @Test
        @Order(1)
        @DisplayName("Ensure Bank Customers array is initially empty")
        public void InitializedBankCustomersEmptyTest(){
            int expected = 0;
            int actual = testBank.Customers.size();
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @Order(2)
        @DisplayName("Ensure Bank Loans array is initially empty")
        public void InitializedBankLoansEmptyTest(){
            int expected = 0;
            int actual = testBank.Loans.size();
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @Order(3)
        @DisplayName("Ensure Bank Accounts array is initially empty")
        public void InitializedBankAccountsEmptyTest(){
            int expected = 0;
            int actual = testBank.Accounts.size();
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @Order(4)
        @DisplayName("Ensure Bank addAccount() adds to Accounts array")
        public void BankAddAccountTest(){
            ArrayList<Account> expected = new ArrayList<>(List.of(checking));
            testBank.addAccount(checking);
            Assertions.assertArrayEquals(expected.toArray(), testBank.Accounts.toArray());
        }

        @Test
        @Order(5)
        @DisplayName("Ensure Bank removeAccount() removes from Accounts array")
        public void BankRemoveAccountTest(){
            ArrayList<Account> expected = new ArrayList<>(List.of(checking));
            testBank.addAccount(checking);
            testBank.addAccount(savings);
            testBank.removeAccount(savings);
            Assertions.assertArrayEquals(expected.toArray(), testBank.Accounts.toArray());
        }

        @Test
        @Order(6)
        @DisplayName("Ensure Bank Accounts Array remains unchanged when removeAccount() removes account not present")
        public void BankRemoveAccountNotPresentTest(){
            ArrayList<Account> expected = new ArrayList<>(List.of(checking));
            testBank.addAccount(checking);
            testBank.removeAccount(savings);
            Assertions.assertArrayEquals(expected.toArray(), testBank.Accounts.toArray());
        }

        @Test
        @Order(7)
        @DisplayName("Ensure Bank addCustomer() adds to the Customer array")
        public void BankAddCustomerTest(){
            ArrayList<Customer> expected = new ArrayList<>(List.of(testCustomer1));
            testBank.addCustomer(testCustomer1);
            Assertions.assertArrayEquals(expected.toArray(), testBank.Customers.toArray());;
        }

        @Test
        @Order(8)
        @DisplayName("Ensure Bank removeCustomer() removes from the Customer array")
        public void BankRemoveCustomerTest(){
            ArrayList<Customer> expected = new ArrayList<>(List.of(testCustomer1));
            testBank.addCustomer(testCustomer1);
            testBank.addCustomer(testCustomer2);
            testBank.removeCustomer(testCustomer2);
            Assertions.assertArrayEquals(expected.toArray(), testBank.Customers.toArray());;
        }

        @Test
        @Order(9)
        @DisplayName("Ensure Bank Customers Array remains unchanged when removeCustomer() removes customer not present")
        public void BankRemoveCustomerNotPresentTest(){
            ArrayList<Customer> expected = new ArrayList<>(List.of(testCustomer2));
            testBank.addCustomer(testCustomer2);
            testBank.removeCustomer(testCustomer1);
            Assertions.assertArrayEquals(expected.toArray(), testBank.Customers.toArray());
        }

        @Test
        @Order(10)
        @DisplayName("Ensure Bank addNewLoan() adds loan to loan array")
        public void BankAddNewLoanTest(){
            testBank.addNewLoan(1000.00);
            int expected = 1;
            Assertions.assertEquals(expected, testBank.Loans.size());
        }

        @Test
        @Order(11)
        @DisplayName("Ensure Bank addNewLoan() < 10000 has interest rate of 10%")
        public void BankAddNewLoan10000Test(){
            testBank.addNewLoan(9999.99);
            double expected = 0.10;
            Assertions.assertEquals(expected, testBank.Loans.get(0).Interest);
        }

        @Test
        @Order(12)
        @DisplayName("Ensure Bank addNewLoan() < 50000 has interest rate of 8%")
        public void BankAddNewLoan50000Test(){
            testBank.addNewLoan(49999.99);
            double expected = 0.08;
            Assertions.assertEquals(expected, testBank.Loans.get(0).Interest);
        }

        @Test
        @Order(13)
        @DisplayName("Ensure Bank addNewLoan() < 200000 has interest rate of 5%")
        public void BankAddNewLoan200000Test(){
            testBank.addNewLoan(199999.99);
            double expected = 0.05;
            Assertions.assertEquals(expected, testBank.Loans.get(0).Interest);
        }

        @Test
        @Order(14)
        @DisplayName("Ensure Bank addNewLoan() < 900000 has interest rate of 3.5%")
        public void BankAddNewLoan900000Test(){
            testBank.addNewLoan(899999.99);
            double expected = 0.035;
            Assertions.assertEquals(expected, testBank.Loans.get(0).Interest);
        }

        @Test
        @Order(15)
        @DisplayName("Ensure Bank addNewLoan() > 900000 throws exception")
        public void BankAddNewLoanOver900000Test(){
            Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                    testBank.addNewLoan(900000.00));

            String expected = "Loan amount too high.";
            String actual = exception.getMessage();
            Assertions.assertTrue(actual.contains(expected));

        }

    }

    @Nested
    @DisplayName("Customer class tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CustomerTest {
        @Test
        @Order(16)
        @DisplayName("Ensure Customer createAccount adds to Accounts array")
        public void CustomerCreateAccountTest(){
            testCustomer1.createAccount(checking);
            ArrayList<Account> expected = new ArrayList<>(List.of(checking));
            Assertions.assertArrayEquals(expected.toArray(), testCustomer1.Accounts.toArray());
        }
    }

    @Nested
    @DisplayName("Customer class tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class LoanTest {
//        TODO Loan test class
    }


}
