package com.example.parag.chatapp;

/**
 * Created by parag on 04-02-2018.
 */

public class Users {
    private String userName;
    private String userEmail;

    public Users() {
    }

    public Users(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
