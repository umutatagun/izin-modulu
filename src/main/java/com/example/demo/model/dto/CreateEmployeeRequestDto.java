package com.example.demo.model.dto;

import com.example.demo.model.Holiday;
import com.example.demo.model.Login;
import com.example.demo.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String tckn;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate = LocalDate.now();
    private Boolean isManager;
    private Holiday holiday = new Holiday();
    private Gender gender;
    private Login login;
}
