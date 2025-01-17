package com.zipcodewilmington;

public class Transactions {
    public static UserVillage selectTransaction(UserVillage userVillage, Account activeAccount) {
        String message = "Options\n\t1 : Check balance\n\t2 : Deposit\n\t3 : Withdraw\n\t4 : Transfer to other account\n\t5 : View transaction history\n\t6 : See last transaction\n\t7 : Close account\n\t0 : Back";
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
                userVillage.getUserById(activeAccount.getUserId()).closeAccountById(activeAccount.getAccountId());
                break;
        }
        return userVillage;
    }

    public static Account depositToAccount(Account activeAccount) {
        Double oldBalance = activeAccount.getBalance();
        Double amountToDeposit = Console.getDoubleInput("Enter deposit amount: $");
        Double newBalance = oldBalance + amountToDeposit;
        activeAccount.setBalance(newBalance);
        // transaction report
        String transactionReport = activeAccount.buildTransactionReport(oldBalance, newBalance, amountToDeposit, "deposit");
        activeAccount.addTransactionReportToTransactionHistory(transactionReport);
        return activeAccount;
    }

    public static Account withDrawFromAccount(Account activeAccount) {
        Double oldBalance = activeAccount.getBalance();
        Double amountToWithdraw;
        do {
            amountToWithdraw = Console.getDoubleInput("Enter amount to withdraw: $");
        } while(!verifyValidAmount(oldBalance, amountToWithdraw));
        Double newBalance = oldBalance - amountToWithdraw;
        activeAccount.setBalance(newBalance);
        String transactionReport = activeAccount.buildTransactionReport(oldBalance, newBalance, amountToWithdraw, "withdraw");
        activeAccount.addTransactionReportToTransactionHistory(transactionReport);
        return activeAccount;
    }

    public static UserVillage selectTransferTarget(UserVillage userVillage, User sourceUser, Account sourceAccount) {
        Console.println("Options\n\t0 : Transfer to your other open accounts\n\t1 : Transfer to another user");
        int choice = Console.getIntegerInput("Select option: ");
        switch (choice) {
            case 0:
                // select from own accounts
                //TODO wrap in if statement
                //if getNumberOfOpenAccounts > 1
                if (sourceUser.getNumberOfOpenAccounts() > 1) {
                    User updatedUser = transferToOwnAccount(sourceUser, sourceAccount);
                    userVillage.updateUser(updatedUser, updatedUser.getUserId());
                }
                break;
            case 1:
                // select another user's account
                userVillage = transferToAnotherUsersAccount(userVillage, sourceUser, sourceAccount);
                break;
        }
        return userVillage;
    }
    //TODO rewrite transfer methods
    public static User transferToOwnAccount(User sourceUser, Account sourceAccount) {
        Account targetAccount = selectCurrentAccount(sourceUser, sourceAccount.getAccountId());
        Double sourceOldBalance = sourceAccount.getBalance();
        Double amountToTransfer;
        do {
            amountToTransfer = Console.getDoubleInput("Enter amount to transfer: $");
        } while (!verifyValidAmount(sourceOldBalance, amountToTransfer));

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

        return sourceUser;
    }

    public static UserVillage transferToAnotherUsersAccount(UserVillage userVillage, User sourceUser, Account sourceAccount) {
        String targetUsername = Console.getStringInput("Enter username you wish to transfer to: ");
        User targetUser = userVillage.getUserByUsername(targetUsername);
        //TODO wrap in if statement
        //check if target user has at least 1 open account
        if (targetUser.getNumberOfOpenAccounts() > 0) {


            Account targetAccount = selectCurrentAccount(targetUser, null);
            Double sourceOldBalance = sourceAccount.getBalance();
            Double amountToTransfer;
            do {
                amountToTransfer = Console.getDoubleInput("Enter amount to transfer: $");
            } while (!verifyValidAmount(sourceOldBalance, amountToTransfer));

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
            String sourceTransactionReport = sourceUser.getAccountById(sourceAccountId).buildTransactionReport(sourceOldBalance, sourceNewBalance, amountToTransfer, "transfer to " + targetUsername);
            sourceUser.getAccountById(sourceAccountId).addTransactionReportToTransactionHistory(sourceTransactionReport);

            // create transaction report for targetAccount
            String targetTransactionReport = targetUser.getAccountById(targetAccountId).buildTransactionReport(targetOldBalance, targetNewBalance, amountToTransfer, "transfer from " + sourceUser.getUsername());
            targetUser.getAccountById(targetAccountId).addTransactionReportToTransactionHistory(targetTransactionReport);

            // update users
            userVillage.updateUser(sourceUser, sourceUser.getUserId());
            userVillage.updateUser(targetUser, targetUser.getUserId());
        }
        return userVillage;
    }

    public static Account selectCurrentAccount(User activeUser, Integer activeAccountId) {
        StringBuilder message = new StringBuilder();
        message.append("Options\n");
        for (Account account : activeUser.getAccounts()) {
            if (account != null && account.checkIfAccountIsOpen() &&
                    (account.getAccountId() != activeAccountId && account.getUserId() == activeUser.getUserId())) {
                message.append(String.format("\t%s : %s\n", account.getAccountId(), account.getName()));
            }
        }
        Console.println(message.toString());
        int accountIndex = Console.getIntegerInput("Select account: ");
        return activeUser.getAccounts()[accountIndex];
    }

    public static Boolean verifyValidAmount(Double balance, Double amount) {
        return (balance > amount);
    }

}
