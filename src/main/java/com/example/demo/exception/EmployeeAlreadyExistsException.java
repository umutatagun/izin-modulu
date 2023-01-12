package com.example.demo.exception;

public class EmployeeAlreadyExistsException extends RuntimeException{
    public EmployeeAlreadyExistsException(String msg) {
        super(msg);
    }
}
