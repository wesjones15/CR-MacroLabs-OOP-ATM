package com.zipcodewilmington;

import java.util.ArrayList;

public class Account {
    private Double balance;
    private Integer userId;
    private Integer accountId;
    private Boolean accountIsOpen = false;
    private ArrayList<String> transactionHistory;
    private static final String[] accountNames = {"Checking", "Savings", "Investments"};

    // constructor
    public Account() {
        this.accountIsOpen = false;
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


}
