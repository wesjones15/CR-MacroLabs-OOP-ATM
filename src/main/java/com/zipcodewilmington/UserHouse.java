package com.zipcodewilmington;

public class UserHouse {

    private  UserWarehouse<User> userHouse = new UserWarehouse<User>();

    public  void add(User user) {
        userHouse.add(user);
    }

    public  void remove(Integer id) {
        userHouse.removeUserById(id);
    }

    public  void remove(User user) {
        userHouse.removeUser(user);
    }

    public  User getUserById(Integer id) {
        return userHouse.getUserById(id);
    }

    public  Integer getNumberOfUsers() {
        return userHouse.getNumberOfUsers();
    }

    public  void clear() {
        userHouse.clear();
    }

}
