package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import com.udacity.jdnd.course3.critter.pet.Pet;

@Entity
public class Customer extends User {
  @OneToMany(mappedBy = "owner")
  private List<Pet> pets;

  @Column(length = 40)
  private String phoneNumber;

  @Column(length = 280)
  private String notes;
}
