package com.example.demo.service;

import com.example.demo.dto.CreateEmployeeRequestDto;
import com.example.demo.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees(Optional<String> email, Optional<String> lastName);
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto createEmployee(CreateEmployeeRequestDto employeeDto);
    void deleteEmployee(Long id);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
}
