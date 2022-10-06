package com.example.demo.message;

public class ResponMessage {
    public String message;

    //poja : plain old java object
    public ResponMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
