package com.example.demo.service.impl;

import com.example.demo.dto.CreatePermissionRequestDto;
import com.example.demo.dto.PermissionDto;
import com.example.demo.exception.EmployeeNotFoundWithEmailException;
import com.example.demo.exception.NotEnoughPermissionException;
import com.example.demo.exception.PermissionNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.Holiday;
import com.example.demo.model.Permission;
import com.example.demo.model.enums.PermissionStatus;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository, ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.permissionRepository = permissionRepository;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<PermissionDto> getAllPermissions(){
        return permissionRepository.findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public PermissionDto createPermissionRequest(CreatePermissionRequestDto createPermissionRequestDto) {
        Permission permission = modelMapper.map(createPermissionRequestDto, Permission.class);

        Optional<Employee> employee = employeeRepository.findByEmail(createPermissionRequestDto.getEmployeeMail());
        if(employee.isEmpty()){
            throw new EmployeeNotFoundWithEmailException(null);
        }

        int permissionDays = (int) calculateWeekdays(createPermissionRequestDto.getFirstDay(), createPermissionRequestDto.getUntilDay());
        Holiday holiday = employee.get().getHoliday();

        Period workingYears = Period.between(employee.get().getStartDate(), LocalDate.now());

        if(workingYears.getYears() == 0) {
            if(permissionDays > holiday.getBeginnerHoliday()) {
                throw new NotEnoughPermissionException(null);
            }
            holiday.setBeginnerHoliday(holiday.getBeginnerHoliday() - permissionDays);
        }
        else {
            if(permissionDays > holiday.getHolidayCount()) {
                throw new NotEnoughPermissionException(null);
            }
            holiday.setHolidayCount(holiday.getHolidayCount() - permissionDays);
        }

        employee.get().setHoliday(holiday);
        permission.setEmployee(employee.get());

        return convert(permissionRepository.save(permission));
    }

    public long test(CreatePermissionRequestDto createPermissionRequestDto) {
        return calculateWeekdays(createPermissionRequestDto.getFirstDay(), createPermissionRequestDto.getUntilDay());
    }

    public PermissionDto updatePermission(Long id,PermissionDto from) {
        Permission permission = findById(id);

        permission.setEmployee(from.getEmployee());
        permission.setEmployeeMail(from.getEmployeeMail());
        permission.setFirstDay(from.getFirstDay());
        permission.setUntilDay(from.getUntilDay());

        return convert(permissionRepository.save(permission));
    }

    public void deletePermission(Long id) {
        findById(id);
        permissionRepository.deleteById(id);
    }

    public PermissionDto approvePermissionRequest(Long id) {
        Permission permission = findById(id);
        permission.setPermissionStatus(PermissionStatus.ONAYLANDI);

        return convert(permissionRepository.save(permission));
    }

    public PermissionDto rejectPermissionRequest(Long id) {
        Permission permission = findById(id);
        permission.setPermissionStatus(PermissionStatus.REDDEDILDI);

        return convert(permissionRepository.save(permission));
    }

    private Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(null));
    }

    private boolean isExists(Long id) {
        return (permissionRepository.findById(id).isPresent()) ? true : false;
    }

    private PermissionDto convert(Permission from) {
        return modelMapper.map(from, PermissionDto.class);
    }

    private Permission convert(PermissionDto from) {
        return modelMapper.map(from, Permission.class);
    }

    private long calculateWeekdays(final LocalDate start, final LocalDate end) {
        DayOfWeek startW = start.getDayOfWeek();
        DayOfWeek endW = end.getDayOfWeek();

        long days = ChronoUnit.DAYS.between(start, end);
        long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }

}
