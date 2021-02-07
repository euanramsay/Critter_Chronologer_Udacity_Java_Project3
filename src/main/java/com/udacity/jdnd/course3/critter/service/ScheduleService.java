package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    private EmployeeRepository employeeRepository;

    private PetRepository petRepository;

    private CustomerRepository customerRepository;

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }
}
