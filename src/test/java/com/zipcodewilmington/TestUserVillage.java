package com.zipcodewilmington;

import org.junit.Test;
import org.junit.Assert;

public class TestUserVillage {
    //TODO create test for constructor
    @Test
    public void constructorTest() {
        UserVillage userVillage = new UserVillage();
        // should initialize users ArrayList and add a default user
        int actual = userVillage.getUsersList().size();
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    //TODO create test for createNewUser
    @Test
    public void createNewUserTest() {
        UserVillage userVillage = new UserVillage(); // default user is added at index 0
        for (int i = 0; i < 10; i++) {
            userVillage.createNewUser("user" + i, "pass" + i);
        }
        int actual = userVillage.getUsersList().size();
        int expected = 11;
        Assert.assertEquals(expected, actual);


    }

    //TODO create test for updateUser
    public void updateUserTest() {

    }

    //TODO create test for getUserById
    public void getUserByIdTest() {

    }

    //TODO create test for getUserByUsername
    public void getUserByUsername() {

    }

    //TODO create test for matchUsernameAndPassword
    public void matchUsernameAndPassword() {

    }

    //TODO create test for getUsersList
    public void getUsersList() {

    }
}
