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

    public Account openAccount(Integer userId, Integer accountId) {
        Double balance = Console.getDoubleInput("Enter your initial balance: $");
        return new Account(balance, userId, accountId);
    }

    //TODO write closeAccount method
    public void closeAccount(Account account, Integer userId) {
        if (account.getBalance() == 0.00 && account.getUserId() == userId) {
            account.accountIsOpen = false;
            String transactionReport = account.buildTransactionReport(0.00,0.00,0.00,"close account");
            account.addTransactionReportToTransactionHistory(transactionReport);
            Console.println("Successfully closed account "+account.getName());
        } else {
             Console.println("your balance must be $0.00 to close your account");
        }
    }

    public Boolean checkIfAccountIsOpen() {
        return this.accountIsOpen;
    }


    public String buildTransactionReport(Double oldBalance, Double newBalance, Double amountTransferred, String transactionType) {
        StringBuilder transactionReport = new StringBuilder();

        transactionReport.append(String.format("Transaction: %s $%.2f\n\tOld Balance:\t$%.2f\n\tNew Balance:\t$%.2f", transactionType, amountTransferred, oldBalance, newBalance));
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
