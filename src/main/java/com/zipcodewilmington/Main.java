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
                Console.println("Options\n\t0 : logout");
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
                if (logUserIn()) {
                    currentUser = userVillage.getUserByUsername(currentUserUsername);
                    userIsLoggedIn = true;
                }
                break;
            case 1:
                //register
                currentUser = createUser();
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
                userIsLoggedIn = false;
                break;
            case 1:
                break;
            case 2:
                break;
        }
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
