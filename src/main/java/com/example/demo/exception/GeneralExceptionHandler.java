package com.example.demo.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    public GeneralExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Locale locale) {
        String errorMsg = messageSource.getMessage("error.exception", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(LoginUsernameAlreadyExistsException.class)
    public ResponseEntity usernameAlreadyExistsException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.employee.username.already.exists", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(LoginInformationRequiredException.class)
    public ResponseEntity loginInformationRequiredException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.employee.login.information.required", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity employeeAlreadyExistsException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.employee.already.exists", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity emailNotFoundException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.employee.not.found", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(PermissionNotFoundException.class)
    public ResponseEntity permissionNotFoundException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.permission.not.found", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundWithEmailException.class)
    public ResponseEntity employeeNotFoundWithEmailException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.employee.not.found.with.email", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughPermissionException.class)
    public ResponseEntity notEnoughPermissionException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.permission.not.enough.permission", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity invalidTokenException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.jwt.invalid.token", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity internalAuthenticationServiceException(Locale locale) {
        String errorMsg = messageSource.getMessage("error.jwt.invalid.auth", null, locale);

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", errorMsg);
        return new ResponseEntity(errors, new HttpHeaders(), BAD_REQUEST);
    }

}
