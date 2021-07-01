package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  public List<Schedule> findAllByPetsContaining(Pet pet);
  public List<Schedule> findAllByEmployeesContaining(Employee employee);
  
  @Query("SELECT s "
       + "FROM Schedule s JOIN s.pets p "
       + "WHERE p.owner = :owner")
  public List<Schedule> findAllWithPetHavingOwner(Customer owner);
}
