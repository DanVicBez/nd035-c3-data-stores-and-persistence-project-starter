package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.jdnd.course3.critter.user.Customer;

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
  
  public Customer getOwnerForPet(Long petId) {
    Pet pet = getById(petId);
    if (pet == null) return null;
    
    return pet.getOwner();
  }
}
