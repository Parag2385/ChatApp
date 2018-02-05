package com.example.parag.chatapp;

/**
 * Created by parag on 04-02-2018.
 */

public class Users {
    private String userName;
    private String userEmail;
    private String userId;

    public Users() {
    }

    public Users(String userName, String userEmail, String userId) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userId = userId;
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
