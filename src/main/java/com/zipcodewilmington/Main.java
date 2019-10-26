package com.zipcodewilmington;

/**
 * Created by iyasuwatts on 10/17/17.
 */
public class Main {
    private static Boolean atmIsRunning = true;

    public static void main(String[] args){
        while(atmIsRunning) {
            Console.println("Welcome to the ATM");
            // 0 : login
            // 1 : register
            // 2 : exit
            Integer action = Console.getIntegerInput("Choose an action: ");
            executeLoginAction(action);


            atmIsRunning = false;
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
            case 1:
                //register
            case 2:
                //exit
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
                break;
            case 1:
                break;
            case 2:
        }
    }
}
