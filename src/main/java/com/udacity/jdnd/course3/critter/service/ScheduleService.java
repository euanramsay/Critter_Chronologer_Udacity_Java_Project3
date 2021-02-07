package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleForPet(long petId) {
        Pet pet = petService.getPetById(petId);
        return scheduleRepository.getAllByPets(pet);
    }

    public List<Schedule> findAllSchedulesForEmployee(long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return scheduleRepository.getAllByEmployeesContains(employee);
    }

    public List<Schedule> findScheduleForCustomer(long customerId) {
        Customer customer = customerService.findById(customerId);
        return scheduleRepository.getAllByPetsIn(customer.getPets());
    }

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }
}
