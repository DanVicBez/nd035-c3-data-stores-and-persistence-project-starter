package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;

@Service
public class ScheduleService {
  @Autowired
  private ScheduleRepository schedules;

  public List<Schedule> getAllForPet(Pet pet) {
    return schedules.findAllByPetsContaining(pet);
  }
  
  public List<Schedule> getAllForEmployee(Employee employee) {
    return schedules.findAllByEmployeesContaining(employee);
  }

  @Transactional
  public Schedule save(Schedule schedule) {
    Schedule saved = schedules.save(schedule);
    if (saved.getEmployees() != null) {
      saved.getEmployees().forEach(e -> {
        e.getSchedules().add(saved);
      });
    }
    
    if (saved.getPets() != null) {
      saved.getPets().forEach(p -> {
        p.getSchedules().add(saved);
      });
    }
    
    return saved;
  }
  
  public List<Schedule> getAll() {
    return schedules.findAll();
  }
  
  public List<Schedule> getAllForCustomer(Customer customer) {
    return schedules.findAllWithPetHavingOwner(customer);
  }
}
