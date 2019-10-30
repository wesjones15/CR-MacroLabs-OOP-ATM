package com.zipcodewilmington;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private Account[] accounts;

    public User(){
        //
    }

    public User( String username, String password, Integer userId){
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.accounts = new Account[3];
        for (int i = 0; i < 3; i++) {
            accounts[i] = new Account();
        }

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

    public void openCheckingAccount() {
        //check if existing account lives here
        accounts[0] = new Checking(0.00, this.getUserId(), 0);
        String message = String.format("\nNew Checking account opened for %s. \n\tCurrent Balance: $ %8.2f", username, accounts[0].getBalance());
        Console.println(message);
    }

    public void openSavingsAccount() {
        //check if existing account lives here
        accounts[1] = new Savings(0.00, this.getUserId(), 1);
        String message = String.format("\nNew Savings account opened for %s. \n\tCurrent Balance: $ %8.2f", username, accounts[1].getBalance());
        Console.println(message);
    }
    public void openInvestmentsAccount() {
        //check if existing account lives here
        accounts[2] = new Investments(0.00, this.getUserId(), 2);
        String message = String.format("\nNew Investments account opened for %s. \n\tCurrent Balance: $ %8.2f", username, accounts[2].getBalance());
        Console.println(message);
    }

    public void closeAccountById(Integer accountId) {
        Account account = this.getAccountById(accountId);
        if (account.getBalance() == 0.00 && account.getUserId() == userId) {
            this.getAccountById(accountId).toggleOpenAccount();
            String transactionReport = account.buildTransactionReport(0.00,0.00,0.00,"close account");
            this.getAccountById(accountId).addTransactionReportToTransactionHistory(transactionReport);
            Console.println("Successfully closed account "+account.getName());
        } else {
            Console.println("Your balance must be $0.00 to close your account.");
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

    public Integer getNumberOfOpenAccounts() {
        int count = 0;
        for (Account account: accounts) {
            if(account.checkIfAccountIsOpen()) {
                count += 1;
            }
        }
        return count;
    }
}
