package com.example.demo.exception;

public class NotEnoughPermissionException extends RuntimeException{
    public NotEnoughPermissionException(String msg) {
        super(msg);
    }
}
