package com.example.parag.chatapp;

/**
 * Created by parag on 05-02-2018.
 */

public class SendBird {

    private static String mUserId;

//    public SendBird(String userId) {
//        this.userId = userId;
//    }

    public static void setUserId(String userId) {
        mUserId = userId;
    }

    public static String getUserId(){

        return mUserId;
    }
}
