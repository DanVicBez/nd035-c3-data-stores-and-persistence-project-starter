package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;

@Entity
public class Pet {
  @Id
  @GeneratedValue
  private Long id;

  private PetType type;
  
  @Column(length = 70)
  private String name;

  @ManyToOne
  private Customer owner;

  private LocalDate birthDate;
  
  @Column(length = 280)
  private String notes;
  
  @ManyToMany(mappedBy = "pets")
  private List<Schedule> schedules;
}
