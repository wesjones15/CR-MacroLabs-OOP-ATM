package com.zipcodewilmington;

public class User {
    private Integer userId;
    private String username;
    private String password;

    public User(){}

    public User( String username, String password, Integer userId){
        this.username = username;
        this.password = password;
        this.userId = userId;
    }
    //ID
    public Integer getUserId(){ return userId;}

    public void setUserId(Integer userId){ this.userId = userId;}
    //PW
    public String getPassword(){return password; }

    public void setPassword(String password){this.password = password; }
    //Username
    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username; }


}
