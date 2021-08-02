package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
  @Autowired
  private ScheduleService scheduleService;

  @Autowired
  private EmployeeService employeeService;
  
  @Autowired
  private CustomerService customerService;

  @Autowired
  private PetService petService;

  @PostMapping
  public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDto) {
    return convertToDto(scheduleService.save(convertFromDto(scheduleDto)));
  }

  @GetMapping
  public List<ScheduleDTO> getAllSchedules() {
    return scheduleService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/pet/{petId}")
  public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
    Pet pet = petService.getById(petId);
    
    if (pet == null) return null;
    
    return scheduleService.getAllForPet(pet).stream().map(this::convertToDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/employee/{employeeId}")
  public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
    return scheduleService.getAllForEmployee(employeeService.getById(employeeId)).stream()
        .map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/customer/{customerId}")
  public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
    return scheduleService.getAllForCustomer(customerService.getById(customerId)).stream().map(this::convertToDto).collect(Collectors.toList());
  }

  private ScheduleDTO convertToDto(Schedule schedule) {
    ScheduleDTO scheduleDto = new ScheduleDTO();
    scheduleDto.setId(schedule.getId());
    scheduleDto.setEmployeeIds(
        schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
    scheduleDto.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
    scheduleDto.setDate(schedule.getDate());
    scheduleDto.setActivities(schedule.getActivities());

    return scheduleDto;
  }

  private Schedule convertFromDto(ScheduleDTO scheduleDto) {
    Schedule schedule = new Schedule();
    schedule.setId(scheduleDto.getId());
    schedule.setEmployees(scheduleDto.getEmployeeIds().stream().map(employeeService::getById)
        .collect(Collectors.toList()));
    schedule.setPets(
        scheduleDto.getPetIds().stream().map(petService::getById).collect(Collectors.toList()));
    schedule.setDate(scheduleDto.getDate());
    schedule.setActivities(scheduleDto.getActivities());

    return schedule;
  }
}
