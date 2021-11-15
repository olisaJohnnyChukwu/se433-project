import bank.Bank;
import customer.Customer;
import org.junit.jupiter.api.*;

@DisplayName("Bryan Morandi test class, testing: Bank, Customer, and Loan")
public class BryanTest {
    Bank testBank;
    Customer testCustomer;


    @BeforeEach
    public void setup() {
        testBank = new Bank("Test Bank");
        testCustomer = new Customer("Cust1", "123 abc Street0", "123-456-7891", "Cust1@gmail.com");
    }
    

}
