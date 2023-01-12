package com.example.demo.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String msg) {
        super(msg);
    }
}
