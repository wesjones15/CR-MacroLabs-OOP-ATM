package com.zipcodewilmington;

public class Transactions {
    public static UserVillage selectTransaction(UserVillage userVillage, Account activeAccount) {
        String message = "Options\n\t1 : check balance\n\t2 : deposit\n\t3 : withdraw\n\t4 : transfer to other account\n\t5 : view transaction history\n\t6 : see last transaction\n\t7 : close account\n\t0 : back";
        Console.println(message);
        Integer action = Console.getIntegerInput("Select option: ");
        switch (action) {
            case 0:
                // back
                break;
            case 1:
                // check balance
                Console.println("Current Balance: $"+activeAccount.getBalance());
                break;
            case 2:
                // deposit
                Console.println("Deposit Into Account "+activeAccount.getName());
                activeAccount = depositToAccount(activeAccount);
                userVillage.updateUserByAccount(activeAccount);
                break;
            case 3:
                // withdraw
                Console.println("Withdraw From Account "+activeAccount.getName());
                activeAccount = withDrawFromAccount(activeAccount);
                userVillage.updateUserByAccount(activeAccount);
                break;
            case 4:
                // transfer to other account
                Console.println("Transfer From Account "+activeAccount.getName());
                selectTransferTarget(userVillage, userVillage.getUserById(activeAccount.getUserId()), activeAccount);
                break;
            case 5:
                // view transaction history
                for (String report : activeAccount.getTransactionHistory()) {
                    Console.println("\n"+report);
                }
                break;
            case 6:
                // view last transaction
                Console.println(activeAccount.getLastTransaction());
                break;
            case 7:
                // close account
                Console.println("Close Account "+activeAccount.getName());
//                closeAccount(activeAccount);
                userVillage.getUserById(activeAccount.getUserId()).closeAccountById(activeAccount.getAccountId());
                break;
        }
//        currentAccount = null;
        return userVillage;
    }

    // params maybe uservillage, accID, userID
    public static Account depositToAccount(Account activeAccount) {
        Double oldBalance = activeAccount.getBalance();
        Double amountToDeposit = Console.getDoubleInput("Enter deposit amount: $");
        Double newBalance = oldBalance + amountToDeposit;
        activeAccount.setBalance(newBalance);
        // transaction report
        String transactionReport = activeAccount.buildTransactionReport(oldBalance, newBalance, amountToDeposit, "deposit");
        activeAccount.addTransactionReportToTransactionHistory(transactionReport);
        //return acc
        return activeAccount;
//        userVillage.updateUser(currentUser, currentUser.getUserId());
    }

    //TODO make withdraw return updated account or updatedUser
    public static Account withDrawFromAccount(Account activeAccount) {
        Double oldBalance = activeAccount.getBalance();
        Double amountToWithdraw = Console.getDoubleInput("Enter amount to withdraw: $");
        Double newBalance = oldBalance - amountToWithdraw;
        activeAccount.setBalance(newBalance);
        String transactionReport = activeAccount.buildTransactionReport(oldBalance, newBalance, amountToWithdraw, "withdraw");
        activeAccount.addTransactionReportToTransactionHistory(transactionReport);
        //return activeUser or activeAccount
        return activeAccount;
//        userVillage.updateUser(currentUser, currentUser.getUserId());
    }

    public static UserVillage selectTransferTarget(UserVillage userVillage, User sourceUser, Account sourceAccount) {
//        Account sourceAccount = currentAccount;
//        User sourceUser = currentUser;
        Console.println("Options\n\t0 : transfer to your other open accounts\n\t1 : transfer to another user");
        int choice = Console.getIntegerInput("Select option: ");
        switch (choice) {
            case 0:
                // select from own accounts
                User updatedUser = transferToOwnAccount(sourceUser, sourceAccount);
                userVillage.updateUser(updatedUser, updatedUser.getUserId());
                break;
            case 1:
                // select another user's account
                userVillage = transferToAnotherUsersAccount(userVillage, sourceUser, sourceAccount);
                break;
        }
        return userVillage;
    }

    public static User transferToOwnAccount(User sourceUser, Account sourceAccount) {
//        User sourceUser = currentUser;
//        Account sourceAccount = currentAccount;
        Account targetAccount = selectCurrentAccount(sourceUser, sourceAccount.getAccountId());
        Double amountToTransfer = Console.getDoubleInput("Enter amount to transfer: $");

        Double sourceOldBalance = sourceAccount.getBalance();
        Double sourceNewBalance = sourceOldBalance - amountToTransfer;

        Double targetOldBalance = targetAccount.getBalance();
        Double targetNewBalance = targetOldBalance + amountToTransfer;

        int targetAccountId = targetAccount.getAccountId();
        sourceUser.getAccountById(targetAccountId).setBalance(targetNewBalance);

        int sourceAccountId = sourceAccount.getAccountId();
        sourceUser.getAccountById(sourceAccountId).setBalance(sourceNewBalance);
        // create transaction report for sourceAccount
        String sourceTransactionReport = sourceUser.getAccountById(sourceAccountId).buildTransactionReport(sourceOldBalance, sourceNewBalance, amountToTransfer, "transfer to "+targetAccount.getName());
        sourceUser.getAccountById(sourceAccountId).addTransactionReportToTransactionHistory(sourceTransactionReport);
        // create transaction report for targetAccount
        String targetTransactionReport = sourceUser.getAccountById(targetAccountId).buildTransactionReport(targetOldBalance,targetNewBalance,amountToTransfer, "transfer from "+sourceAccount.getName());
        sourceUser.getAccountById(targetAccountId).addTransactionReportToTransactionHistory(targetTransactionReport);
        // update user
//        userVillage.updateUser(sourceUser, sourceUser.getUserId());
        return sourceUser;
    }

    public static UserVillage transferToAnotherUsersAccount(UserVillage userVillage, User sourceUser, Account sourceAccount) {
//        User sourceUser = currentUser;
//        Account sourceAccount = currentAccount;

        String targetUsername = Console.getStringInput("Enter username you wish to transfer to: ");
        User targetUser = userVillage.getUserByUsername(targetUsername);
        Account targetAccount = selectCurrentAccount(targetUser, null);

        Double amountToTransfer = Console.getDoubleInput("Enter amount to transfer: $");

        Double sourceOldBalance = sourceAccount.getBalance();
        Double sourceNewBalance = sourceOldBalance - amountToTransfer;

        Double targetOldBalance = targetAccount.getBalance();
        Double targetNewBalance = targetOldBalance + amountToTransfer;

        // withdraw from sourceAccount
        // deposit to targetAccount
        // update accounts
        int targetAccountId = targetAccount.getAccountId();
        targetUser.getAccountById(targetAccountId).setBalance(targetNewBalance);

        int sourceAccountId = sourceAccount.getAccountId();
        sourceUser.getAccountById(sourceAccountId).setBalance(sourceNewBalance);

        // create transaction report for sourceAccount
        String sourceTransactionReport = sourceUser.getAccountById(sourceAccountId).buildTransactionReport(sourceOldBalance, sourceNewBalance, amountToTransfer, "transfer to "+targetUsername);
        sourceUser.getAccountById(sourceAccountId).addTransactionReportToTransactionHistory(sourceTransactionReport);

        // create transaction report for targetAccount
        String targetTransactionReport = targetUser.getAccountById(targetAccountId).buildTransactionReport(targetOldBalance,targetNewBalance,amountToTransfer, "transfer from "+sourceUser.getUsername());
        targetUser.getAccountById(targetAccountId).addTransactionReportToTransactionHistory(targetTransactionReport);

        // update users
        userVillage.updateUser(sourceUser, sourceUser.getUserId());
        userVillage.updateUser(targetUser, targetUser.getUserId());
        return userVillage;
    }

    public static Account selectCurrentAccount(User activeUser, Integer activeAccountId) {
        // might not need activeAccountId

        StringBuilder message = new StringBuilder();
        message.append("Options\n");
        for (Account account : activeUser.getAccounts()) {
            if (account != null && (account.getAccountId() != activeAccountId && account.getUserId() == activeUser.getUserId())) {
                message.append(String.format("\t%s : %s\n", account.getAccountId(), account.getName()));
            }
        }
        Console.println(message.toString());
        int accountIndex = Console.getIntegerInput("select account: ");
        return activeUser.getAccounts()[accountIndex];
    }

}
