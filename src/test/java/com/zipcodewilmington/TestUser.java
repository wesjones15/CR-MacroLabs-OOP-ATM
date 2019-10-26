package com.zipcodewilmington;

import org.junit.Assert;
import org.junit.Test;

public class TestUser {

    @Test
    public void testSetUserID(){
        User user = new User("TestUser", null);
        String expected = "TestUser";
        user.setUserId(expected);
        String actual  = user.getUserId();
        Assert.assertEquals(expected,actual);
    }
}
