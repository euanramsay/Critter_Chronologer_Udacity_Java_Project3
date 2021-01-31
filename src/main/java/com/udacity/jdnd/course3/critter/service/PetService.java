package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private PetRepository petRepository;

    public Optional<Pet> findById(long id) {
        return petRepository.findById(id);
    }
}
