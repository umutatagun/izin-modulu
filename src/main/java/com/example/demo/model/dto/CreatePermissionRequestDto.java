package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermissionRequestDto {
    private String employeeMail;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate firstDay;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate untilDay;
}
