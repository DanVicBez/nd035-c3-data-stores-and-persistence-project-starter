package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.jdnd.course3.critter.pet.Pet;

@Service
public class ScheduleService {
  @Autowired
  private ScheduleRepository schedules;
  
  public List<Schedule> getAllForPet(Pet pet) {
    return schedules.findAllByPetsContaining(pet);
  }
}
