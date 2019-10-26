package com.zipcodewilmington;

public class User
{
    private Integer id;
    private String password;
    private String username;

    public User(String username, String password, Integer id) //constructor
    {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public Integer getId()
    {

        return id;
    }
}
