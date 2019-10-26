package com.zipcodewilmington;

import java.util.Date;
import com.zipcodewilmington.UserWarehouse;

import static com.zipcodewilmington.UserWarehouse.*;


public class UserFactory
{

    public User createUser(String username, String password) {
        Integer newId = UserWarehouse.getNumberOfUsers();
        return new User(username, password, newId);
    }

}
