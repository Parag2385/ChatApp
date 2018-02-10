package com.example.parag.chatapp.models;

/**
 * Created by parag on 04-02-2018.
 */

public class ChatMessage {

    private String message;
    private String receiverEmail;
    private String receiverName;
    private String receiverUID;
    private String senderName;
    private String photoUrl;
    private String senderUid;
    private long time;

    public ChatMessage() {
    }

    public ChatMessage(String message, String photoUrl, String receiverEmail, String receiverName, String receiverUID,
                       String senderName, String senderUid, long time, String firebaseToken) {
        this.message = message;
        this.photoUrl = photoUrl;
        this.receiverEmail = receiverEmail;
        this.receiverName = receiverName;
        this.receiverUID = receiverUID;
        this.senderName = senderName;
        this.senderUid = senderUid;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getReceiverUID() {
        return receiverUID;
    }

    public void setReceiverUID(String receiverUID) {
        this.receiverUID = receiverUID;
    }


}
