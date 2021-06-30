package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.udacity.jdnd.course3.critter.pet.Pet;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  public List<Schedule> findAllByPetsContaining(Pet pet);
}
