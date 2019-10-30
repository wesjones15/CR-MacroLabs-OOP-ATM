package com.zipcodewilmington;

import org.junit.Assert;
import org.junit.Test;

public class TestUser {

    @Test
    public void testDefaultUserConstructor(){
        User user = new User();
        //ID
        Integer expectedId = 0;
        user.setUserId(expectedId);
        Integer actualId  = user.getUserId();

        //PW
        String expectedPassword = "TestPassword";
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();

        //Username
        String expectedUsername = "TestUser";
        user.setUsername(expectedUsername);
        String actualUsername = user.getUsername();

        Assert.assertEquals(expectedId,actualId);
        Assert.assertEquals(expectedPassword,actualPassword);
        Assert.assertEquals(expectedUsername,actualUsername);

    }

    @Test
    public void testUserConstructor(){
        User user = new User("TestUser", "TestPassowrd",0);
        //ID
        Integer expectedId = 0;
        user.setUserId(expectedId);
        Integer actualId  = user.getUserId();

        //PW
        String expectedPassword = "TestPassword";
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();

        //Username
        String expectedUsername = "TestUser";
        user.setUsername(expectedUsername);
        String actualUsername = user.getUsername();

        Assert.assertEquals(expectedId,actualId);
        Assert.assertEquals(expectedPassword,actualPassword);
        Assert.assertEquals(expectedUsername,actualUsername);

    }

    @Test
    public void testSetUserID(){
        User user = new User(null,null,1);
        Integer expected = 1;
        user.setUserId(expected);
        Integer actual  = user.getUserId();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void openCheckingAccountTest1() {
        User user = new User("wes", "wes", 1);
        user.openCheckingAccount();
        Double actual = user.getCheckingAccount().getBalance();
        Double expected = 0.00;
        Assert.assertEquals(expected, actual, 2);
    }
//    @Test
//    public void openCheckingAccountTest2() {
//        User user = new User("wes", "wes", 1);
//        user.openCheckingAccount();
//        // try to open checking account when account is already open
//        user.openCheckingAccount();
//    }

    @Test
    public void openSavingsAccountTest() {
        User user = new User("wes", "wes", 1);
        user.openSavingsAccount();
        Double actual = user.getSavingsAccount().getBalance();
        Double expected = 0.00;
        Assert.assertEquals(expected, actual, 2);
    }

    @Test
    public void openInvestmentsAccountTest() {
        User user = new User("wes", "wes", 1);
        user.openInvestmentsAccount();
        Double actual = user.getInvestmentsAccount().getBalance();
        Double expected = 0.00;
        Assert.assertEquals(expected, actual, 2);
    }

    @Test
    public void getAccountsTest() {
        User user = new User("wes", "wes", 1);
        user.openCheckingAccount();
        user.openSavingsAccount();
        user.openInvestmentsAccount();
        for (Account account : user.getAccounts()) {
            Assert.assertTrue(account instanceof Account);
        }
    }

    @Test
    public void getCheckingAccountTest() {
        User user = new User("wes", "wes", 1);
        user.openCheckingAccount();
        Account account = user.getCheckingAccount();
        Assert.assertTrue(account instanceof Checking);
    }

    @Test
    public void getSavingsAccountTest() {
        User user = new User("wes", "wes", 1);
        user.openSavingsAccount();
        Account account = user.getSavingsAccount();
        Assert.assertTrue(account instanceof Savings);
    }

    @Test
    public void getInvestmentsAccountTest() {
        User user = new User("wes", "wes", 1);
        user.openInvestmentsAccount();
        Account account = user.getInvestmentsAccount();
        Assert.assertTrue(account instanceof Investments);
    }

    @Test
    public void TestHasOpenAccount(){
        User user = new User("Bilbo","Baggins",1);
        user.openCheckingAccount();
        //Expected true
        Assert.assertTrue(user.hasOpenAccount());
    }

    @Test
    public void TestHasOpenAccount1(){
        User user = new User("Bilbo","Baggins",1);
        user.openCheckingAccount();
        user.getCheckingAccount().closeAccount(user.getCheckingAccount(),1);
        //Expected false
        Assert.assertFalse(user.hasOpenAccount());
    }
}
