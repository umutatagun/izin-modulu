package com.example.demo.controller;

import com.example.demo.service.SchedulePermissions;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    private final SchedulePermissions schedulePermissions;
    private final MessageSource messageSource;

    public TestController(SchedulePermissions schedulePermissions, MessageSource messageSource) {
        this.schedulePermissions = schedulePermissions;
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity<Void> calculateNextYearsPermissions() {
        schedulePermissions.addPermissions();
        return new ResponseEntity(OK);
    }

    @GetMapping("/2")
    public String calc(Locale locale){
        return messageSource.getMessage("errors.email.required", null, locale);
    }
}
