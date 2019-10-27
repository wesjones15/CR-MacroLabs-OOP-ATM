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
                break;
            case 2:
                // open account
                break;
        }
    }

    public static void chooseAccountAction(int action) {
        StringBuilder message = new StringBuilder();
        message.append("Choose your account\n");
        for (Account account : currentUser.getAccounts()) {
            if (account.checkIfAccountIsOpen()) {

            }
        }
        //checking
        //savings
        //investments
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
