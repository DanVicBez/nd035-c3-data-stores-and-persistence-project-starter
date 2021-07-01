package com.udacity.jdnd.course3.critter.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// table-per-class inheritance strategy was chosen because having a User table just to store 2
// fields felt unneccessary and would add complexity to SQL queries requiring additional joins
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 70)
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
