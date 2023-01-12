package com.example.demo.exception;

public class LoginInformationRequiredException extends RuntimeException{
    public LoginInformationRequiredException(String msg) {
        super(msg);
    }
}
