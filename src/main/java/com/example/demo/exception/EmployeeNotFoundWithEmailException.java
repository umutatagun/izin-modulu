package com.example.demo.exception;

public class EmployeeNotFoundWithEmailException extends RuntimeException{
    public EmployeeNotFoundWithEmailException(String msg) {
        super(msg);
    }
}
