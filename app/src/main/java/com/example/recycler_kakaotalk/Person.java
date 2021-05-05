package com.example.recycler_kakaotalk;

public class Person {
    int picture;
    String name;
    String message;

    public Person(int picture, String name, String message) {
        this.picture = picture;
        this.name = name;
        this.message = message;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}