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
}
