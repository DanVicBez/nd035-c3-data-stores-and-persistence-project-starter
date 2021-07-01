package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.udacity.jdnd.course3.critter.user.Customer;

@Service
public class PetService {
  @Autowired
  private PetRepository pets;

  @Transactional
  public Pet save(Pet pet) {
    Pet saved = pets.save(pet);

    if (saved.getOwner() != null) {
      saved.getOwner().getPets().add(saved);
    }

    return saved;
  }
  
  public Pet getById(Long petId) {
    return pets.findById(petId).orElse(null);
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
