import org.ericsunu.BankAccount;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class BankAccountTest {
    BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        bankAccount = new BankAccount();
    }

    @Test
    @DisplayName("Should Return Account Balance")
    public void shouldReturnBalance() {
        var expectedBalance = 5000.0;
        bankAccount.setBalance(expectedBalance);
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Should Set Account Balance")
    public void shouldSetAccountBalance() {
        var expectedBalance = 5000.0;
        bankAccount.setBalance(expectedBalance);
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Nested
    @DisplayName("Deposit's Happy Path Tests")
    class DepositHappyPathTests {
        @Test
        @DisplayName("Should Deposit To Account")
        public void shouldDepositToAccount() {
            var initialBalance = 0.0;
            bankAccount.setBalance(initialBalance);
            var depositAmount = 7000.00;
            bankAccount.deposit(depositAmount);
            assertEquals(depositAmount, bankAccount.getBalance());
        }

        @Test
        @DisplayName("Should Deposit To Account With A Certain Initial Balance")
        public void shouldDepositToAccountWithACertainInitialBalance() {
            var initialBalance = 456.0;
            bankAccount.setBalance(initialBalance);
            var depositAmount = 800.0;
            bankAccount.deposit(depositAmount);
            assertEquals(1256.0, bankAccount.getBalance());
        }
    }

    @Nested
    @DisplayName("Deposit's Edge Case Tests")
    class DepositEdgeCaseTests {

        @Test
        @DisplayName("When Amount Is A Huge Positive Number")
        public void shouldDepositToAccountWithAHugePositiveNumber() {
            var depositAmount = 500000000000.0;
            bankAccount.deposit(depositAmount);
            assertEquals(depositAmount, bankAccount.getBalance());
        }

        @Test
        @DisplayName("When Deposit Amount is Negative")
        public void shouldDepositToAccountWithANegativeAmount() {
            var depositAmount = -5000.0;
            assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(depositAmount));
        }

    }
}
