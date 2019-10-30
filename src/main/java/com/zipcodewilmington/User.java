package com.zipcodewilmington;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private Account[] accounts;

    public User(){}

    public User( String username, String password, Integer userId){
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.accounts = new Account[3];//checking, savings, investment

    }
    //ID
    public Integer getUserId(){ return userId;}

    public void setUserId(Integer userId){ this.userId = userId;}
    //PW
    public String getPassword(){return password; }

    public void setPassword(String password){this.password = password; }
    //Username
    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username; }

    //TODO write method for openAccountById
//    public void openAccountById(Integer accountId) {
//        accounts[accountId] = new Account(00.00, this.getUserId(), accountId);
//    }

    public void openCheckingAccount() {
        //check if existing account lives here
        accounts[0] = new Checking(0.00, this.getUserId(), 0);
        String message = String.format("New Checking account opened for %s, \n Current Balance: $ %8.2f", username, accounts[0].getBalance());
        Console.println(message);
    }
    // set boolean accountIsOpen to true

    public void openSavingsAccount() {
        //check if existing account lives here
        accounts[1] = new Savings(0.00, this.getUserId(), 1);
        String message = String.format("New Savings account opened for %s, \nCurrent Balance: $ %8.2f", username, accounts[1].getBalance());
        Console.println(message);
    }
    public void openInvestmentsAccount() {
        //check if existing account lives here
        accounts[2] = new Investments(0.00, this.getUserId(), 2);
        String message = String.format("New Investments account opened for %s, \n Current Balance: $ %8.2f", username, accounts[2].getBalance());
        Console.println(message);
    }
    //TODO write method for close checking account
    // verify account is empty
    // replace accounts[0] with empty checking account object
    // set boolean accountIsOpen to false

    public void closeAccountById(Integer accountId) {
        Account account = this.getAccountById(accountId);
        if (account.getBalance() == 0.00 && account.getUserId() == userId) {
            this.getAccountById(accountId).toggleOpenAccount();
            String transactionReport = account.buildTransactionReport(0.00,0.00,0.00,"close account");
            this.getAccountById(accountId).addTransactionReportToTransactionHistory(transactionReport);
            Console.println("Successfully closed account "+account.getName());
        } else {
            Console.println("your balance must be $0.00 to close your account");
        }
    }

    public Account[] getAccounts() {return accounts;}

    public Account getAccountById(Integer accountId) {
        return accounts[accountId];
    }

    public Account getCheckingAccount() {
        return this.accounts[0];
    }

    public Account getSavingsAccount() {
        return this.accounts[1];
    }

    public Account getInvestmentsAccount() {
        return this.accounts[2];
    }
}
