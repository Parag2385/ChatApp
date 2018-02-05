package com.example.parag.chatapp;

/**
 * Created by parag on 04-02-2018.
 */

public class ChatMessage {

    private String text;
    private String name;
    private String photoUrl;
    private String userId;
    private long time;

    public ChatMessage() {
    }

    public ChatMessage(String text, String name, String photoUrl, String userId, long time) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.userId = userId;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
