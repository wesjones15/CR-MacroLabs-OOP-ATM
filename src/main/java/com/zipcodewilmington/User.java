package com.zipcodewilmington;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private Account[] accounts;

    public User(){}

    public User( String username, String password, Integer userId){
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.accounts = new Account[3];//checking, savings, investment
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

    //TODO write method for open checking account

    //TODO write method for open savings account

    //TODO write method for open investments account

    //TODO write method for close checking account :

    //TODO write method for close savings account

    //TODO write method for close investments account

    //TODO write method for get checking account : return accounts[0]

    //TODO write method for get savings account : return accounts[1]

    //TODO write method for get investments account : return accounts[2]

}
