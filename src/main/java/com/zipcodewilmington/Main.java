package com.zipcodewilmington;

/**
 * Created by iyasuwatts on 10/17/17.
 */
public class Main {
    private static Boolean atmIsRunning = true;
    private static UserVillage userVillage = new UserVillage();
    private static Boolean userIsLoggedIn = false;
    private static String currentUserUsername;
    private static Integer action;

    public static void main(String[] args){
        Console.println("Welcome to the ATM");

        while(atmIsRunning) {
            Console.println("Options\n\t0 : Login\n\t1 : Register\n\t2 : Exit ATM");
            action = Console.getIntegerInput("Choose an action: ");
            User activeUser = executeLoginAction(action);
            if (userIsLoggedIn) Console.println(String.format("Hello %s, you are now logged in", currentUserUsername));

            while (userIsLoggedIn) {
                Console.println("Current User: " + activeUser.getUsername());
                Console.println("Options\n\t1 : Access account\n\t2 : Open account\n\t3 : Delete user\n\t0 : Logout");
                action = Console.getIntegerInput("Choose an action: ");
                Console.clear();
                executeUserAction(activeUser, action);
            }
        }
    }

    public static User executeLoginAction(int action) {
        User activeUser = null;
        // When no user logged in
        switch (action) {
            case 0:
                //login
                //TODO move this into logUserIn method
                if (logUserIn()) {
                    activeUser = userVillage.getUserByUsername(currentUserUsername);
                    userIsLoggedIn = true;
                }
                break;
            case 1:
                //register
                //TODO move this into createUser method maybe
                activeUser = createUser();
                Console.println(String.format("Welcome %s",activeUser.getUsername()));
                userIsLoggedIn = true;
                break;
            case 2:
                //exit
                atmIsRunning = false;
                break;
        }
        return activeUser;
    }
    public static void executeUserAction(User activeUser, int action) {
        switch (action) {
            case 0:
                // logout
                userVillage.updateUser(activeUser,activeUser.getUserId());
                userIsLoggedIn = false;
                currentUserUsername = "";
                break;
            case 1:
                // access account
                if (activeUser.getNumberOfOpenAccounts() > 0) {
                    Account activeAccount = Transactions.selectCurrentAccount(activeUser, null);
                    userVillage = Transactions.selectTransaction(userVillage, activeAccount);
                    break;
                }
            case 2:
                // open account
                int accountIndex = selectAccountToOpen(activeUser);
                activeUser = openAccount(activeUser, accountIndex);
                userVillage.updateUser(activeUser, activeUser.getUserId());
                break;
            case 3:
                Console.println("Removing user " + currentUserUsername);
                Console.println("Returning to main menu.\n");
                userVillage.removeUser(activeUser.getUserId());
                userIsLoggedIn = false;
                currentUserUsername = "";
                break;
        }
    }

    public static Integer selectAccountToOpen(User activeUser) {
        String[] accountNames = {"Checking", "Savings", "Investments"};
        StringBuilder message = new StringBuilder();
        message.append("Options\n");
        for (int i = 0; i < activeUser.getAccounts().length; i++) {
            if (!activeUser.getAccountById(i).checkIfAccountIsOpen()) {
                message.append(String.format("\t%s : %s\n", i, accountNames[i]));
            }
        }
        Console.println("Open Account\n");
        Console.println(message.toString());
        //TODO add check for if acc already open
        int accountIndex = Console.getIntegerInput("Select account: ");
        return accountIndex;
    }

    public static User openAccount(User activeUser, int accountIndex) {
        switch(accountIndex) {
            case 0:
                activeUser.openCheckingAccount();
                break;
            case 1:
                activeUser.openSavingsAccount();
                break;
            case 2:
                activeUser.openInvestmentsAccount();
                break;
        }
        return activeUser;
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
