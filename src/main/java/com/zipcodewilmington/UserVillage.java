package com.zipcodewilmington;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class UserVillage /*implements Serializable */{
    private ArrayList<User> users;

    public UserVillage() {
        this.users = new ArrayList<User>();
        createNewUser("zippy", "pw123");
    }

    public void createNewUser(String username, String password) {
        if (verifyUsernameNotTaken(username)) {
            users.add(new User(username, password, users.size()));
        }

    }

    public void updateUser(User updatedUser, Integer userId) {
        String currentUserUsername = getUserById(userId).getUsername();
        String updatedUserUsername = updatedUser.getUsername();
        // this makes sure that the correct user is being updated
        if (currentUserUsername.equals(updatedUserUsername)) {
            users.set(userId, updatedUser);
        }
        else {
            Console.println("updateUser failed. usernames do not match");
        }
    }

    public void removeUser(Integer userId) {
        users.set(userId, new User());
    }

    public void updateUserByAccount(Account account) {
        Integer userId = account.getUserId();
        User user = getUserById(userId);
        updateUser(user, userId);
    }

    public User getUserById(Integer userId) {
        return users.get(userId);
    }

    public User getUserByUsername(String username) {
        int userId = 0;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                break;
            }
            userId += 1;
        }
        if (userId+1 > users.size()) {
            userId = 0;
            Console.println("user not found, returning default user instead of breaking");
            // should raise user not found exception
        }
        return users.get(userId);
    }

    public Boolean matchUsernameAndPassword(String username, String password) {
        User user = getUserByUsername(username);    // if username is not found, it will be caught here
        Boolean loggedIn = false;
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            loggedIn = true;
        }
        return loggedIn;
    }

    public ArrayList<User> getUsersList() {
        return users;
    }

    public Boolean verifyUsernameNotTaken(String username) {
        boolean uniqueUsername = true;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                uniqueUsername = false;
                break;
            }
        }
        return uniqueUsername;
    }

//    public void WriteObjectToFile(Object serObj) {
//
//        try {
//
//            FileOutputStream fileOut = new FileOutputStream(filepath);
//            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//            objectOut.writeObject(serObj);
//            objectOut.close();
//            System.out.println("The Object  was succesfully written to a file");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
