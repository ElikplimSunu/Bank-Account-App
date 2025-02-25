package org.ericsunu;

public class BankAccount {
    private double balance;

    // Constructor initializes account with zero balance.
    public BankAccount() {
        this.balance = 0.0;
    }

    // Returns the current balance.
    public double getBalance() {
        return balance;
    }

    // Deposits a positive amount into the account.
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    // Withdraws a positive amount if sufficient funds are available.
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    // Transfers a specified amount from this account to another account.
    public void transfer(BankAccount target, double amount) {
        if (target == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        // Reuse withdraw and deposit to perform the transfer.
        this.withdraw(amount);
        target.deposit(amount);
    }
}

