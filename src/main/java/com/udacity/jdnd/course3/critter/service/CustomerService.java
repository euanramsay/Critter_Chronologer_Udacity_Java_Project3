package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetService petService;

    public Customer findById(long id) {
        return customerRepository.findById(id).orElseThrow
                (() -> new RuntimeException(String.format("Customer with id %s does not exist in customer table", id)));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerByPetId(long petId) {
        Pet pet = petService.findPetById(petId);
        return pet.getCustomer();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
