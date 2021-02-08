package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule newSchedule = convertScheduleDTOToScheduleEntity(scheduleDTO);
        Schedule savedSchedule = scheduleService.saveSchedule(newSchedule);
        return convertScheduleEntityToScheduleDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.findAllSchedules();
        return convertListOfScheduleEntitiesToScheduleDTOs(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.findAllSchedulesForPet(petId);
        return convertListOfScheduleEntitiesToScheduleDTOs(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.findAllSchedulesForEmployee(employeeId);
        return convertListOfScheduleEntitiesToScheduleDTOs(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.findAllSchedulesForCustomer(customerId);
        return convertListOfScheduleEntitiesToScheduleDTOs(schedules);
    }

    private ScheduleDTO convertScheduleEntityToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Employee> employees = schedule.getEmployees();
        List<Long> employeeIds = employees
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        scheduleDTO.setEmployeeIds(employeeIds);
        List<Pet> pets = schedule.getPets();
        List<Long> petIds = pets
                .stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }

    private Schedule convertScheduleDTOToScheduleEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Employee> employees = employeeIds
                .stream()
                .map(employeeId -> employeeService.findEmployeeById(employeeId))
                .collect(Collectors.toList());
        schedule.setEmployees(employees);
        List<Long> petIds = scheduleDTO.getPetIds();
        List<Pet> pets = petIds
                .stream()
                .map(petId -> petService.findPetById(petId))
                .collect(Collectors.toList());
        schedule.setPets(pets);
        return schedule;
    }

    private List<ScheduleDTO> convertListOfScheduleEntitiesToScheduleDTOs(List<Schedule> schedules) {
        return schedules
                .stream()
                .map(this::convertScheduleEntityToScheduleDTO)
                .collect(Collectors.toList());
    }
}
