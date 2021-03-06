package com.example.recycler_kakaotalk.data;

import java.io.Serializable;

public class ChatData implements Serializable {
    private String chat_profile;
    private String chat_name;
    private String chat_message;
    private String chat_time;

    public ChatData(String profile, String name, String msg, String time) {
        this.chat_profile = profile;
        this.chat_name = name;
        this.chat_message = msg;
        this.chat_time = time;
    }

    public ChatData() {
    }

    public String getChat_profile() {
        return chat_profile;
    }

    public void setChat_profile(String chat_profile) {
        this.chat_profile = chat_profile;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String iv_name) {
        this.chat_name = chat_name;
    }

    public String getChat_message() {
        return chat_message;
    }

    public void setChat_message(String iv_message) {
        this.chat_message = chat_message;
    }

    public String getChat_Time() {
        return chat_time;
    }

    public void setChat_Time(String time) {
        this.chat_time = time;
    }
}