package com.zipcodewilmington;

import java.util.ArrayList;

public class Account {
    public Double balance;
    private Integer userId;
    private Integer accountId;
    private Boolean accountIsOpen = false;
    private ArrayList<String> transactionHistory;
    private static final String[] accountNames = {"Checking", "Savings", "Investments"};

    public Account(Double balance) {
        this.balance = balance;
        this.userId = 0;
        this.accountId = 0;
        this.accountIsOpen = true;
        this.transactionHistory = new ArrayList<String>();
    }
    public Account(Double balance, Integer userId, Integer accountId) {
        this.balance = balance;
        this.userId = userId;
        this.accountId = accountId;
        this.accountIsOpen = true;
        this.transactionHistory = new ArrayList<String>();
    }

    public Account() {
        this.accountIsOpen = false;
    }

    public void toggleOpenAccount() { this.accountIsOpen = !this.accountIsOpen; }

    public Boolean checkIfAccountIsOpen() {
        return this.accountIsOpen;
    }


    public String buildTransactionReport(Double oldBalance, Double newBalance, Double amountTransferred, String transactionType) {
        return String.format("Transaction: %s $%.2f\n\tOld Balance:\t$%.2f\n\tNew Balance:\t$%.2f",
                transactionType, amountTransferred, oldBalance, newBalance);
    }

    public void addTransactionReportToTransactionHistory(String transactionReport) {
        transactionHistory.add(transactionReport);
        Console.println("\n\n"+transactionReport+"\n\n");
    }

    public ArrayList<String> getTransactionHistory() {
        return this.transactionHistory;
    }


    public String getLastTransaction() {
        return this.transactionHistory.get(transactionHistory.size()-1);
    }

    public void setBalance(Double newBalance) {
        this.balance = newBalance;
    }

    public Double getBalance() {
        return this.balance;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getAccountId() { return this.accountId; }

    public String getName() { return accountNames[this.accountId];}

    public Boolean closeAccount() {
        Boolean closed = false;
        if (!(this.balance() > 0.00)) {
            closed = true;
        }
        return closed;
    }

    public Double balance() {
        return this.balance;
    }

    public void deposit(Double amount) {
        if (amount > 0.0) {
            Double newBal = this.balance() + amount;
            this.setBalance(newBal);
        }
    }

    public Double withdraw(Double amount) {
        Double newBal = 0.0;
        if (this.balance() >= amount && amount > 0.0) {
            newBal = this.balance() - amount;
            this.setBalance(newBal);
        }
        return newBal;
    }

    public void transfer(Account target, Double amount) {
        if (this.balance() >= amount && amount > 0.0) {
            target.deposit(amount);
            this.withdraw(amount);
        }
    }


}
