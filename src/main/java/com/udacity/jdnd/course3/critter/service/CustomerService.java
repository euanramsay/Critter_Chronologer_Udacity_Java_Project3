package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Customer findById(long id) {
        return customerRepository.findById(id).orElseThrow
                (() -> new RuntimeException(String.format("Customer with id %s does not exist in customer table", id)));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerByPetId(long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow
                (() -> new RuntimeException(String.format("Pet with id %s does not exist in pet table", petId)));
        return pet.getCustomer();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
