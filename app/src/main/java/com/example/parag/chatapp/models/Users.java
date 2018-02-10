package com.example.parag.chatapp.models;

/**
 * Created by parag on 04-02-2018.
 */

public class Users {
    private String userName;
    private String userEmail;
    private String userId;
    private String userToken;

    public Users() {
    }

    public Users(String userName, String userEmail, String userId, String token) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userToken = token;

    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserToken() {
        return userToken;
    }
}
