package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import com.udacity.jdnd.course3.critter.schedule.Schedule;

@Entity
public class Employee extends User {
  @ElementCollection
  private Set<EmployeeSkill> skills;

  @ElementCollection
  private Set<DayOfWeek> daysAvailable;
  
  @ManyToMany(mappedBy = "employees")
  private List<Schedule> schedules;
}
