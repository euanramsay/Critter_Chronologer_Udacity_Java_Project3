package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }
}
