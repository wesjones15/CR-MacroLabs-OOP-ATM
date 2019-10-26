package com.zipcodewilmington;

import java.util.ArrayList;


class UserWarehouse<UserType extends User> {
    private volatile ArrayList<UserType> list = new ArrayList<UserType>();

    public void add(UserType user) {
        list.add(user);
    }

    public UserType getUserById(int id) {
        for(UserType user : list) {
            if(user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    public void removeUser(User user) {
        list.remove(user);
    }

    public void removeUserById(int id) {
        removeUser(getUserById(id));
    }

    public static Integer getNumberOfUsers() {
       // return list.size(); //need to check on this .. commented to make it work for now
        return 0;
    }

    public void clear() {
        list.clear();
    }
}
