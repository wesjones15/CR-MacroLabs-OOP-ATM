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
    //ID
    public String getUserId(){ return userId;}

    public void setUserId(String userId){ this.userId = userId;}
    //PW
    public String getPassword(){return password; }

    public void setPassword(String password){this.password = password; }

}
