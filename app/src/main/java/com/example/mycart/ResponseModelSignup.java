package com.example.mycart;

public class ResponseModelSignup {
    String message;

    public ResponseModelSignup(String message) {
        this.message = message;
    }

    public ResponseModelSignup() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
