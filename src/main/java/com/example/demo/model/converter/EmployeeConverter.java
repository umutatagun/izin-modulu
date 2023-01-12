package com.example.demo.model.converter;

import com.example.demo.model.Employee;
import com.example.demo.model.dto.CreateEmployeeRequestDto;
import com.example.demo.model.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeConverter {

    private final ModelMapper modelMapper;

    public EmployeeConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmployeeDto convert(Employee from) {
        return modelMapper.map(from, EmployeeDto.class);
    }

    public Employee convert(EmployeeDto from) {
        return modelMapper.map(from, Employee.class);
    }

    public Employee convert(CreateEmployeeRequestDto from) {
        return modelMapper.map(from, Employee.class);
    }

    public List<EmployeeDto> convert(List<Employee> fromList) {
        return fromList.stream()
                .map(from -> modelMapper.map(from, EmployeeDto.class))
                .collect(Collectors.toList());
    }
}
