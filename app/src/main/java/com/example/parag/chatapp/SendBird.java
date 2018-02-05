package com.example.parag.chatapp;

/**
 * Created by parag on 05-02-2018.
 */

public class SendBird {

    private static String mUserId;
    private static String toUserID;

    public static void setCurrentUserId(String userId) {
        mUserId = userId;
    }

    public static String getCurrnetUserId(){

        return mUserId;
    }

    public static String getFriendUserID() {
        return toUserID;
    }

    public static void setFriendUserID(String userID) {
        toUserID = userID;
    }
}
