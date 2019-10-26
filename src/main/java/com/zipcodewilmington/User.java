package com.zipcodewilmington;

public class User {
    private String userId;
    private String username;
    private String password;

    public User(){}

    public User( String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUserId(){ return userId;}

    public void setUserId(String userId){ this.userId = userId;}

}
