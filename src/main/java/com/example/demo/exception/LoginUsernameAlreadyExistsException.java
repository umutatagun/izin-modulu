package com.example.demo.exception;

public class LoginUsernameAlreadyExistsException extends RuntimeException{
    public LoginUsernameAlreadyExistsException(String msg) {
        super(msg);
    }
}
