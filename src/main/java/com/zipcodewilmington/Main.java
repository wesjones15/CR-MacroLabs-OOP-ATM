package com.zipcodewilmington;

/**
 * Created by iyasuwatts on 10/17/17.
 */
public class Main {
    private static Boolean atmIsRunning = true;
    private static UserVillage userVillage = new UserVillage();
    private static Boolean userIsLoggedIn = false;
    private static User currentUser;
    private static String currentUserUsername;
    private static Integer action;
    private static Account currentAccount;
    private static final String[] accountNames = {"Checking", "Savings", "Investments"};

    public static void main(String[] args){
        Console.println("Welcome to the ATM");

        while(atmIsRunning) {
            Console.println("Options\n\t0 : login\n\t1 : register\n\t2 : exit");
            action = Console.getIntegerInput("Choose an action: ");
            executeLoginAction(action);

            while (userIsLoggedIn) {
                // do something
                Console.println(String.format("hello %s, you are now logged in", currentUserUsername));


                Console.println("Options\n\t1 : access account\n\t2 : open account\n\t0 : logout");
                action = Console.getIntegerInput("Choose an action: ");
                executeUserAction(action);
            }



        }
        //while
       //login or register
       //display acc info
       //logout, transfer
        
    }

    public static void executeLoginAction(int action) {
        // When no user logged in
        switch (action) {
            case 0:
                //login
                //TODO move this into logUserIn method
                if (logUserIn()) {
                    currentUser = userVillage.getUserByUsername(currentUserUsername);
                    userIsLoggedIn = true;
                }
                break;
            case 1:
                //register
                //TODO move this into createUser method maybe
                currentUser = createUser();
                Console.println(String.format("Welcome %s",currentUser.getUsername()));
                userIsLoggedIn = true;
                break;
            case 2:
                //exit
                atmIsRunning = false;
                break;
        }
    }
    public static void executeUserAction(int action) {
        switch (action) {
            // open account
            // close account
            // access account
                // choose account
                    // check balance
                    // deposit
                    // withdraw
                    // transfer
                        // to account 0 1 2
                        //
            case 0:
                // logout
                userVillage.updateUser(currentUser,currentUser.getUserId());
//                Console.println(currentUser.getUsername()+" "+currentUser.getUserId());
                userIsLoggedIn = false;
                currentUser = null;
                currentUserUsername = "";
                break;
            case 1:
                // access account
                currentAccount = selectCurrentAccount(currentUser);
                selectTransaction();
                break;
            case 2:
                // open account
                int accountIndex = selectAccountToOpen();
                openAccount(accountIndex);
                break;
        }
    }

    public static void selectTransaction() {
        String message = "Options\n\t1 : check balance\n\t2 : deposit\n\t3 : withdraw\n\t4 : transfer to other account\n\t0 : back";
        Console.println(message);
        Integer action = Console.getIntegerInput("Select option: ");
        switch (action) {
            case 0:
                // back
                break;
            case 1:
                // check balance
                Console.println("Current Balance: $"+currentAccount.getBalance());
                break;
            case 2:
                // deposit
                Console.println("Deposit Into Account "+accountNames[currentAccount.getAccountId()]);
                depositToAccount();

                break;
            case 3:
                // withdraw
                Console.println("Withdraw From Account "+accountNames[currentAccount.getAccountId()]);
                withDrawFromAccount();
                break;
            case 4:
                // transfer to other account
                Console.println("Transfer From Account "+accountNames[currentAccount.getAccountId()]);
                selectTransferTarget();
                break;
        }
        currentAccount = null;
    }

    public static void depositToAccount() {
        Double oldBalance = currentAccount.getBalance();
        Double amountToDeposit = Console.getDoubleInput("Enter deposit amount: ");
        Double newBalance = oldBalance + amountToDeposit;
        currentAccount.setBalance(newBalance);
        // transaction report
        String transactionReport = currentAccount.buildTransactionReport(oldBalance, newBalance, amountToDeposit, "deposit");
        currentAccount.addTransactionReportToTransactionHistory(transactionReport);
        Console.println(transactionReport);
        userVillage.updateUser(currentUser, currentUser.getUserId());
    }

    public static void withDrawFromAccount() {
        Double oldBalance = currentAccount.getBalance();
        Double amountToWithdraw = Console.getDoubleInput("Enter amount to withdraw: ");
        Double newBalance = oldBalance - amountToWithdraw;
        currentAccount.setBalance(newBalance);
        String transactionReport = currentAccount.buildTransactionReport(oldBalance, newBalance, amountToWithdraw, "withdraw");
        currentAccount.addTransactionReportToTransactionHistory(transactionReport);
        Console.println(transactionReport);
        userVillage.updateUser(currentUser, currentUser.getUserId());
    }

    public static void selectTransferTarget() {
        Account sourceAccount = currentAccount;
        User sourceUser = currentUser;
        Console.println("Options\n\t0 : transfer to your other open accounts\n\t1 : transfer to another user");
        int choice = Console.getIntegerInput("Select option: ");
        switch (choice) {
            case 0:
                // select from own accounts
                transferToOwnAccount();
                break;
            case 1:
                // select another user's account
                transferToAnotherUsersAccount();
                break;
        }
    }

    public static void transferToOwnAccount() {
        User sourceUser = currentUser;
        Account sourceAccount = currentAccount;
        Account targetAccount = selectCurrentAccount(sourceUser);
        Double amountToTransfer = Console.getDoubleInput("Enter amount to transfer: ");

        Double sourceOldBalance = sourceAccount.getBalance();
        Double sourceNewBalance = sourceOldBalance - amountToTransfer;

        Double targetOldBalance = targetAccount.getBalance();
        Double targetNewBalance = targetOldBalance + amountToTransfer;

        int targetAccountId = targetAccount.getAccountId();
        sourceUser.getAccounts()[targetAccountId].setBalance(targetNewBalance);
        int sourceAccountId = sourceAccount.getAccountId();
        sourceUser.getAccounts()[sourceAccountId].setBalance(sourceNewBalance);
        // create transaction report for sourceAccount
        String sourceTransactionReport = sourceUser.getAccounts()[sourceAccountId].buildTransactionReport(sourceOldBalance, sourceNewBalance, amountToTransfer, "transfer to "+accountNames[targetAccountId]);
        sourceUser.getAccounts()[sourceAccountId].addTransactionReportToTransactionHistory(sourceTransactionReport);
        // create transaction report for targetAccount
        String targetTransactionReport = sourceUser.getAccounts()[targetAccountId].buildTransactionReport(targetOldBalance,targetNewBalance,amountToTransfer, "transfer from "+accountNames[sourceAccountId]);
        sourceUser.getAccounts()[targetAccountId].addTransactionReportToTransactionHistory(targetTransactionReport);
        // update users
        userVillage.updateUser(sourceUser, sourceUser.getUserId());
        // create transaction report

        // update user
        userVillage.updateUser(sourceUser, sourceUser.getUserId());
    }

    public static void transferToAnotherUsersAccount() {
        User sourceUser = currentUser;
        Account sourceAccount = currentAccount;
        String targetUsername = Console.getStringInput("Enter username you wish to transfer to: ");
        User targetUser = userVillage.getUserByUsername(targetUsername);
        Account targetAccount = selectCurrentAccount(targetUser);
        Double amountToTransfer = Console.getDoubleInput("Enter amount to transfer: ");
        Double sourceOldBalance = sourceAccount.getBalance();
        Double sourceNewBalance = sourceOldBalance - amountToTransfer;
        Double targetOldBalance = targetAccount.getBalance();
        Double targetNewBalance = targetOldBalance + amountToTransfer;

        // withdraw from sourceAccount
        // deposit to targetAccount
        // update accounts
        int targetAccountId = targetAccount.getAccountId();
        targetUser.getAccounts()[targetAccountId].setBalance(targetNewBalance);
        int sourceAccountId = sourceAccount.getAccountId();
        sourceUser.getAccounts()[sourceAccountId].setBalance(sourceNewBalance);
        // create transaction report for sourceAccount
        String sourceTransactionReport = sourceUser.getAccounts()[sourceAccountId].buildTransactionReport(sourceOldBalance, sourceNewBalance, amountToTransfer, "transfer to "+targetUsername);
        sourceUser.getAccounts()[sourceAccountId].addTransactionReportToTransactionHistory(sourceTransactionReport);
        // create transaction report for targetAccount
        String targetTransactionReport = targetUser.getAccounts()[targetAccountId].buildTransactionReport(targetOldBalance,targetNewBalance,amountToTransfer, "transfer from "+sourceUser.getUsername());
        targetUser.getAccounts()[targetAccountId].addTransactionReportToTransactionHistory(targetTransactionReport);
        // update users
        userVillage.updateUser(sourceUser, sourceUser.getUserId());
        userVillage.updateUser(targetUser, targetUser.getUserId());
    }

    public static Account selectCurrentAccount(User user) {
        StringBuilder message = new StringBuilder();
        message.append("Options\n");
        for (int i = 0; i < user.getAccounts().length; i++) {
            if (user.getAccounts()[i] != null && user.getAccounts()[i] != currentAccount) {
                message.append(String.format("\t%s : %s\n", i, accountNames[i]));
            }
        }
        Console.println(message.toString());
        int accountIndex = Console.getIntegerInput("select account: ");
        return user.getAccounts()[accountIndex];
    }

    public static Integer selectAccountToOpen() {
        StringBuilder message = new StringBuilder();
        message.append("Options\n");
        for (int i = 0; i < currentUser.getAccounts().length; i++) {
            if (currentUser.getAccounts()[i] == null) {
                message.append(String.format("\t%s : %s\n", i, accountNames[i]));
            }
        }
        Console.println(message.toString());
        int accountIndex = Console.getIntegerInput("select account: ");
        return accountIndex;
    }

    public static void openAccount(int accountIndex) {
        // check if account is not already open
        switch(accountIndex) {
            case 0:
                currentUser.openCheckingAccount();
                break;
            case 1:
                currentUser.openSavingsAccount();
                break;
            case 2:
                currentUser.openInvestmentsAccount();
                break;
        }
        userVillage.updateUser(currentUser, currentUser.getUserId());
    }


    public static Boolean logUserIn() {
        currentUserUsername = Console.getStringInput("username: ");
        String password = Console.getStringInput("password: ");
        return userVillage.matchUsernameAndPassword(currentUserUsername,password);
    }
    public static User createUser() {
        currentUserUsername = Console.getStringInput("username: ");
        String password = Console.getStringInput("password: ");
        userVillage.createNewUser(currentUserUsername, password);
        return userVillage.getUserByUsername(currentUserUsername);
    }

}
