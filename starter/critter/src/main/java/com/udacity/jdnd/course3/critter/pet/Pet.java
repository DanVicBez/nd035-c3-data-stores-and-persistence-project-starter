package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;
import java.util.ArrayList;
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
  private List<Schedule> schedules = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PetType getType() {
    return type;
  }

  public void setType(PetType type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Customer getOwner() {
    return owner;
  }

  public void setOwner(Customer owner) {
    this.owner = owner;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public List<Schedule> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<Schedule> schedules) {
    this.schedules = schedules;
  }
}
