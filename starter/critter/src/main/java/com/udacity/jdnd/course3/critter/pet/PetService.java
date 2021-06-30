package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
  @Autowired
  private PetRepository pets;

  public Pet save(Pet pet) {
    return pets.save(pet);
  }
  
  public Pet getById(Long petId) {
    return pets.findById(petId).get();
  }
  
  public List<Pet> getAll() {
    return pets.findAll();
  }
}
