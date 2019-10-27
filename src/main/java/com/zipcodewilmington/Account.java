package com.zipcodewilmington;

import java.util.ArrayList;

public class Account {
    private Double balance;
    private Integer userId;
    private Boolean accountIsOpen;
    private ArrayList<String> transactionHistory;

    // constructor
    public Account(Double balance, Integer userId) {
        this.balance = balance;
        this.userId = userId;
        this.accountIsOpen = true;
        this.transactionHistory = new ArrayList<String>();
    }

    public Account openAccount(Integer userId) {
        Double balance = Console.getDoubleInput("Enter your initial balance: ");
        return new Account(balance, userId);
    }

    //TODO write closeAccount method

    public Boolean checkIfAccountIsOpen() {
        return this.accountIsOpen;
    }

    public String buildTransactionReport(Double oldBalance, Double newBalance, Double amountTransferred, String transactionType) {
        StringBuilder transactionReport = new StringBuilder();
        //TODO write method
        // need different report structure depending on type of transaction
        // withdraw $xx.xx from xxxx account
        // previous balance: $xx.xx ::: current balance: $xx.xx
        // .
        // transfer $xx.xx from xxxx account to yyyy account
        // previous balance: $xx.xx ::: current balance: $xx.xx (for xxxx account)

        return transactionReport.toString();
    }

    public void addTransactionReportToTransactionHistory(String transactionReport) {
        transactionHistory.add(transactionReport);
    }

    public ArrayList<String> getTransactionHistory() {
        return this.transactionHistory;
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

}
