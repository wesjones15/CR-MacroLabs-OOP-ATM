package com.zipcodewilmington;

import org.junit.Assert;
import org.junit.Test;

public class TestUser {

    @Test
    public void testDefaultUserConstructor(){
        User user = new User();
        //ID
        String expectedName = "TestUser";
        user.setUserId(expectedName);
        String actualName  = user.getUserId();

        //PW
        String expectedPassword = "TestPassword";
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();

        Assert.assertEquals(expectedName,actualName);
        Assert.assertEquals(expectedPassword,actualPassword);

    }

    @Test
    public void testUserConstructor(){
        User user = new User("TestUser", "TestPassowrd");
        //ID
        String expectedName = "TestUser";
        user.setUserId(expectedName);
        String actualName  = user.getUserId();

        //PW
        String expectedPassword = "TestPassword";
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();

        Assert.assertEquals(expectedName,actualName);
        Assert.assertEquals(expectedPassword,actualPassword);

    }

    @Test
    public void testSetUserID(){
        User user = new User("TestUser", null);
        String expected = "TestUser";
        user.setUserId(expected);
        String actual  = user.getUserId();
        Assert.assertEquals(expected,actual);
    }
}
