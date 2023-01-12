package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulePermissions {
    private final EmployeeRepository employeeRepository;

    @Value("${permissions.junior}")
    private Integer juniorPermissionCount;

    @Value("${permissions.mid}")
    private Integer midPermissionCount;

    @Value("${permissions.senior}")
    private Integer seniorPermissionCount;

    public SchedulePermissions(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addPermissions() {
        List<Employee> employeeList = employeeRepository.findAll();

        List<Employee> juniorEmployees = employeeList.stream()
                .filter(employee -> {
                    Period period = Period.between(employee.getStartDate(), LocalDate.now());
                    return (period.getYears() > 0 && period.getYears() < 6);
                })
                .map(employee -> {
                    Integer currentHolidayCount = employee.getHoliday().getHolidayCount();
                    employee.getHoliday().setHolidayCount(currentHolidayCount + juniorPermissionCount);
                    return employee;
                })
                .collect(Collectors.toList());

        List<Employee> midEmployees = employeeList.stream()
                .filter(employee -> {
                    Period period = Period.between(employee.getStartDate(), LocalDate.now());
                    return (period.getYears() > 5 && period.getYears() < 11);
                })
                .map(employee -> {
                    Integer currentHolidayCount = employee.getHoliday().getHolidayCount();
                    employee.getHoliday().setHolidayCount(currentHolidayCount + midPermissionCount);
                    return employee;
                })
                .collect(Collectors.toList());

        List<Employee> seniorEmployees = employeeList.stream()
                .filter(employee -> {
                    Period period = Period.between(employee.getStartDate(), LocalDate.now());
                    return period.getYears() > 10;
                })
                .map(employee -> {
                    Integer currentHolidayCount = employee.getHoliday().getHolidayCount();
                    employee.getHoliday().setHolidayCount(currentHolidayCount + seniorPermissionCount);
                    return employee;
                })
                .collect(Collectors.toList());

        employeeRepository.saveAll(juniorEmployees);
        employeeRepository.saveAll(midEmployees);
        employeeRepository.saveAll(seniorEmployees);
    }

    // every year at 30 December
    @Scheduled(cron = "0 0 0 30 12 ?")
    public void yearlyScheduledJob() {
        addPermissions();
    }


}
