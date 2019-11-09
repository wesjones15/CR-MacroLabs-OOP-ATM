package com.zipcodewilmington;

import org.junit.Test;
import org.junit.Assert;

public class UserVillageTest {
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
//    @Test
//    public void updateUserTest() {
//        UserVillage userVillage = new UserVillage();
//        userVillage.createNewUser("wes","password");
//        int userId = userVillage.getUserByUsername("wes").getUserId();
//        User updatedUser = userVillage.getUserByUsername("wes");
//        userVillage.updateUser(updatedUser, userId);
//        TODO finish this test after accounts are added for users
//    }

    //TODO write test for updateUser where username is paired with incorrect userId
//    @Test
//    public void updateUserIdMismatchTest() {
//        UserVillage userVillage = new UserVillage();
//        userVillage.createNewUser("wes","password");
//        int userId = userVillage.getUserByUsername("zippy").getUserId();
//        User updatedUser = userVillage.getUserByUsername("wes");
//        userVillage.updateUser(updatedUser, userId);
//    }
    //TODO create test for getUserById
    @Test
    public void getUserByIdTest1() {
        UserVillage userVillage = new UserVillage();
        String actualUsername = userVillage.getUserById(0).getUsername();
        String expectedUsername = "zippy";
        Assert.assertEquals(expectedUsername, actualUsername);
    }
    @Test
    public void getUserByIdTest2() {
        UserVillage userVillage = new UserVillage();
        userVillage.createNewUser("wes","wes");
        String actualUsername = userVillage.getUserById(1).getUsername();
        String expectedUsername = "wes";
        Assert.assertEquals(expectedUsername, actualUsername);
    }
    //TODO create test for getUserByUsername
    @Test
    public void getUserByUsername1() {
        UserVillage userVillage = new UserVillage();
        userVillage.createNewUser("wes","wes");
        int actualUserId = userVillage.getUserByUsername("wes").getUserId();
        int expectedUserId = 1;
        Assert.assertEquals(expectedUserId, actualUserId);
    }
    @Test
    public void getUserByUsername2() {
        UserVillage userVillage = new UserVillage();
        userVillage.createNewUser("wes","wes");
        userVillage.createNewUser("mike","mike");
        userVillage.createNewUser("usha","usha");
        int actualUserId = userVillage.getUserByUsername("mike").getUserId();
        int expectedUserId = 2;
        Assert.assertEquals(expectedUserId, actualUserId);
    }

    //TODO create test for matchUsernameAndPassword
    @Test
    public void matchUsernameAndPassword1() {
        UserVillage userVillage = new UserVillage();
        userVillage.createNewUser("wes","wes");
        Assert.assertTrue(userVillage.matchUsernameAndPassword("wes", "wes"));
    }

    @Test
    public void matchUsernameAndPassword2() {
        UserVillage userVillage = new UserVillage();
        userVillage.createNewUser("wes","P4ssword");
        Assert.assertFalse(userVillage.matchUsernameAndPassword("wes", "wes"));
    }

    //TODO create test for getUsersList
    @Test
    public void getUsersList() {
        UserVillage userVillage = new UserVillage();
        int actual = userVillage.getUsersList().size();
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    //TODO write tests for verifyUsernameNotTaken
    @Test
    public void verifyUsernameNotTakenTest1() {
        UserVillage userVillage = new UserVillage();
        Assert.assertTrue(userVillage.verifyUsernameNotTaken("wes"));
    }

    @Test
    public void verifyUsernameNotTakenTest2() {
        UserVillage userVillage = new UserVillage();
        Assert.assertFalse(userVillage.verifyUsernameNotTaken("zippy"));
    }
}
