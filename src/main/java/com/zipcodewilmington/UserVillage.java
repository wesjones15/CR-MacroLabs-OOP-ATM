package com.zipcodewilmington;

import java.util.ArrayList;

public class UserVillage {
    private ArrayList<User> users;

    public UserVillage() {
        this.users = new ArrayList<User>();
        createNewUser("zippy", "pw123");
    }

    public void createNewUser(String username, String password) {
        users.add(new User(username, password, users.size()));
    }

    public void updateUser(User updatedUser, Integer userId) {
        Boolean verifySameUser = false;
        String currentUserUsername = getUserById(userId).getUsername();
        String updatedUserUsername = updatedUser.getUsername();
        // this makes sure that the correct user is being updated
        if (currentUserUsername == updatedUserUsername) users.set(userId, updatedUser);
        else Console.println("updateUser failed. usernames do not match");
    }

    public User getUserById(Integer userId) {
        return users.get(userId);
    }

    public User getUserByUsername(String username) {
        int userId = 0;
        for (User user : users) {
            userId += 1;
            if (user.getUsername() == username) {
                break;
            }
        }
        if (userId+1 > users.size()) {
            // should raise user not found exception
        }
        return users.get(userId);
    }

    public Boolean matchUsernameAndPassword(String username, String password) {
        User user = getUserByUsername(username);    // if username is not found, it will be caught here
        Boolean loggedIn = false;
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) loggedIn = true;
        return loggedIn;
    }

    public ArrayList<User> getUsersList() {
        return users;
    }
}
