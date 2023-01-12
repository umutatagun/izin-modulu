package com.example.demo.exception;

public class PermissionNotFoundException extends RuntimeException{
    public PermissionNotFoundException(String msg) {
        super(msg);
    }
}
