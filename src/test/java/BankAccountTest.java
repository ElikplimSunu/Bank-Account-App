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

        @Test
        @DisplayName("When Amount Is String")
        public void shouldDepositToAccountWithAString() {
            var depositAmount = "-500.0";
            assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(Double.parseDouble(depositAmount)));
        }
    }


    @Nested
    @DisplayName("Tests For Withdrawal Functionality")
    class WithdrawTests {

        @Test
        @DisplayName("Should Withdraw Successful")
        public void shouldWithdrawSuccessful() {
            var initialBalance = 5000.0;
            var withdrawalAmount = 450.0;
            var expectedBalance = 4550.0;
            bankAccount.setBalance(initialBalance);
            bankAccount.withdraw(withdrawalAmount);
            assertEquals(expectedBalance, bankAccount.getBalance());
        }

        @Test
        @DisplayName("Should Withdraw A Certain Negative Number")
        public void shouldWithdrawNegativeNumber() {
            var initialBalance = 5000.0;
            var withdrawalAmount = -450.0;
            bankAccount.setBalance(initialBalance);
            assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(withdrawalAmount));
        }

        @Test
        @DisplayName("Should Withdraw An Amount Larger Than Balance")
        public void shouldWithdrawAnAmountLargerThanBalance() {
            var initialBalance = 5000.0;
            var withdrawalAmount = 7000.0;
            bankAccount.setBalance(initialBalance);
            assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(withdrawalAmount), "Insufficient funds");
        }
    }

    @Nested
    @DisplayName("Test Cases for Transfer Functionality")
    class TransferTests {
        BankAccount bankAccount1;
        BankAccount bankAccount2;

        @BeforeEach
        public void setup() {
             bankAccount1 = new BankAccount();
             bankAccount2 = new BankAccount();
        }

        @Test
        @DisplayName("Should Transfer Successful")
        public void shouldTransferSuccessful() {
            var initialBalanceForAccount1 = 6000.0;
            var initialBalanceForAccount2 = 4000.0;
            var transferAmount =  5000.0;
            var expectedBalanceForAccount2 = 9000.0;
            var expectedBalanceForAccount1 = 1000.0;
            bankAccount1.setBalance(initialBalanceForAccount1);
            bankAccount2.setBalance(initialBalanceForAccount2);
            bankAccount1.transfer(bankAccount2, transferAmount);
            assertEquals(expectedBalanceForAccount2, bankAccount2.getBalance());
            assertEquals(expectedBalanceForAccount1, bankAccount1.getBalance());
        }

        @Test
        @DisplayName("When Transfer Target is Null")
        public void shouldNotTransferWhenTargetIsNull() {
            var initialBalanceForAccount1 = 6000.0;
            var transferAmount =  5000.0;
            bankAccount1.setBalance(initialBalanceForAccount1);
            assertThrows(IllegalArgumentException.class, () -> bankAccount1.transfer(null, transferAmount));
        }
    }
}
