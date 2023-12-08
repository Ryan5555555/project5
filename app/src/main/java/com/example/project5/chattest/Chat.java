package com.example.project5.chattest;

import android.net.Uri;

import com.google.firebase.Timestamp;

public class Chat {
    private String sender;
    private String receiver;
    private String message;
    private String image;
    private Timestamp timestamp; // 将数据类型更改为 Timestamp

    public Chat(String sender, String receiver, String message, String image, Timestamp timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.image = image;
        this.timestamp = timestamp;
    }

    public Chat() {
        // 空构造函数
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}