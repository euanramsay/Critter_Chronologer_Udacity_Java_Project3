package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(long id) {
        return petRepository.findById(id).orElseThrow
                (() -> new RuntimeException(String.format("Pet with id %s does not exist in pet table", id)));
    }

    public List<Pet> getPetsByCustomerId(Long customerId) {
        return petRepository.getAllByCustomerId(customerId);
    }

    public Pet savePet(Pet pet, long ownerId) {
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        Pet savedPet = petRepository.save(pet);
        customer.addPet(savedPet);
        customerRepository.save(customer);
        return savedPet;
    }
}
