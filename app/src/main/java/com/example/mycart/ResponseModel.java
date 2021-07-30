package com.example.mycart;

public class ResponseModel {
    String message;

    public ResponseModel(String message) {
        this.message = message;
    }

    public ResponseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
