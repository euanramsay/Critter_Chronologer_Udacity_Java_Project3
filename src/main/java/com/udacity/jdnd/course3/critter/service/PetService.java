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

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public Pet findPetById(long id) {
        return petRepository.findById(id).orElseThrow
                (() -> new RuntimeException(String.format("Pet with id %s does not exist in pet table", id)));
    }

    public List<Pet> findPetsByCustomerId(Long customerId) {
        return petRepository.getAllByCustomerId(customerId);
    }

    public Pet savePet(Pet pet) {
        petRepository.save(pet);
        return pet;
    }
}
