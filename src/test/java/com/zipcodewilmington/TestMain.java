package com.zipcodewilmington;

import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;

public class TestMain {
    //TODO write test for logUserIn method
    //TODO write test for createUser
    //TODO write tests for main flow

@Test
    public void TestTransferToOwnAccount(){
    User user = new User("Bilbo","Baggins",1);
    user.openCheckingAccount();
    user.getAccountsToString();
    user.getCheckingAccount().closeAccount(user.getCheckingAccount(),1);

    if(user.hasOpenAccount()){
        Console.println("Has open account");
    }
    Console.println(user.getCheckingAccount().getBalance().toString());
    Console.println(user.getUserId().toString()  + "\n" + user.getUsername() + "\n" + user.getPassword());
    Console.println(Arrays.toString(user.getAccounts()));
}
}