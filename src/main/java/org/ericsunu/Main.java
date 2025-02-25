package org.ericsunu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();

        System.out.println("Welcome to the Bank Account Application!");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. View Account1 Balance");
            System.out.println("2. Deposit into Account1");
            System.out.println("3. Withdraw from Account1");
            System.out.println("4. Transfer from Account1 to Account2");
            System.out.println("5. View Account2 Balance");
            System.out.println("6. Exit");
            System.out.print("Your choice: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Account1 Balance: $" + account1.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        account1.deposit(depositAmount);
                        System.out.println("Deposit successful! New Account1 Balance: $" + account1.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    try {
                        account1.withdraw(withdrawalAmount);
                        System.out.println("Withdrawal successful! New Account1 Balance: $" + account1.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter transfer amount from Account1 to Account2: ");
                    double transferAmount = scanner.nextDouble();
                    try {
                        account1.transfer(account2, transferAmount);
                        System.out.println("Transfer successful!");
                        System.out.println("Account1 Balance: $" + account1.getBalance());
                        System.out.println("Account2 Balance: $" + account2.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Account2 Balance: $" + account2.getBalance());
                    break;
                case 6:
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}