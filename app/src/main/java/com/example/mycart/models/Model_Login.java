package com.example.mycart.models;

public class Model_Login {
    String message;

    public Model_Login(String message) {
        this.message = message;
    }

    public Model_Login() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

