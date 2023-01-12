package com.example.demo.model.dto;

import com.example.demo.model.Employee;
import com.example.demo.model.enums.PermissionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate firstDay;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate untilDay;
    @JsonIgnore
    private Employee employee;
    private String employeeMail;
    private PermissionStatus permissionStatus = PermissionStatus.ONAY_BEKLENIYOR;
}
