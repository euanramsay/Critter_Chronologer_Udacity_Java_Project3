package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee findEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow
                (() -> new RuntimeException(String.format("Employee with id %s does not exist in employee table", id)));
    }

    public List<Employee> findAvailableEmployees(LocalDate date, Set<EmployeeSkill> skills) {
        List<Employee> employees = employeeRepository
                .findAllByDaysAvailableContains(date.getDayOfWeek()).stream()
                .filter((employee -> employee.getSkills().containsAll(skills)))
                .collect(Collectors.toList());
        return employees;
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
