package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class Schedule {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToMany
  private List<Employee> employees;

  @ManyToMany
  private List<Pet> pets;

  private LocalDate date;

  @ElementCollection
  private Set<EmployeeSkill> activities;
}
