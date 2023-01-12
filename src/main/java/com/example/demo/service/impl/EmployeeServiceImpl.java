package com.example.demo.service.impl;

import com.example.demo.exception.LoginInformationRequiredException;
import com.example.demo.exception.LoginUsernameAlreadyExistsException;
import com.example.demo.model.converter.EmployeeConverter;
import com.example.demo.model.dto.CreateEmployeeRequestDto;
import com.example.demo.model.dto.EmployeeDto;
import com.example.demo.exception.EmployeeAlreadyExistsException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.Holiday;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter converter;
    private final BCryptPasswordEncoder encoder;
    private final LoginRepository loginRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeConverter converter, BCryptPasswordEncoder encoder, LoginRepository loginRepository) {
        this.employeeRepository = employeeRepository;
        this.converter = converter;
        this.encoder = encoder;
        this.loginRepository = loginRepository;
    }

    public List<EmployeeDto> getAllEmployees() {
        return converter.convert(employeeRepository.findAll());
    }


    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = findById(id);
        return converter.convert(employee);
    }

    public EmployeeDto createEmployee(CreateEmployeeRequestDto employeeDto) {
        if(isExists(employeeDto.getEmail())) {
            throw new EmployeeAlreadyExistsException(null);
        }
        if(Objects.isNull(employeeDto.getLogin())) {
            throw new LoginInformationRequiredException(null);
        }
        if(loginRepository.findByUsername(employeeDto.getLogin().getUsername()).isPresent()) {
            throw new LoginUsernameAlreadyExistsException(null);
        }
        employeeDto.getLogin().setPassword(encoder.encode(employeeDto.getLogin().getPassword()));
        Employee employee = converter.convert(employeeDto);

        Period diff = Period.between(employeeDto.getStartDate(), LocalDate.now());
        if(diff.getYears() == 0) {
            Holiday holiday = new Holiday();
            holiday.setBeginnerHoliday(5);
            employee.setHoliday(holiday);
        }

        return converter.convert(employeeRepository.save(employee));
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto from) {
        Employee employee = findById(id);
        employee.setEmail(from.getEmail());
        employee.setFirstName(from.getFirstName());
        employee.setLastName(from.getLastName());
        employee.setBirthDate(from.getBirthDate());
        employee.setIsManager(from.getIsManager());
        employee.setStartDate(from.getStartDate());
        employee.setHoliday(from.getHoliday());
        employee.setGender(from.getGender());
        employee.setLastModifiedDate(LocalDate.now());
        employee.setLastModifiedBy("Admin");

        return converter.convert(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        findById(id);
        employeeRepository.deleteById(id);
    }


    private boolean isExists(String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        return (employee.isPresent()) ? true : false;
    }

    private Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(null));
    }

}

