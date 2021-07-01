package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employees;
  
  public Employee save(Employee employee) {
    return employees.save(employee);
  }

  public Employee getById(Long employeeId) {
    return employees.findById(employeeId).orElse(null);
  }
 
  public void setAvailabilityForEmployee(Long employeeId, Set<DayOfWeek> daysAvailable) {
    Employee employee = getById(employeeId);
    
    if (employee != null) {
      employee.setDaysAvailable(daysAvailable);
    }
  }
  
  public List<Employee> getAvailableEmployees(Set<EmployeeSkill> skills, LocalDate date) {
    List<Employee> availableOnDay = employees.findByDaysAvailableContaining(date.getDayOfWeek());
    return availableOnDay.stream().filter(e -> e.getSkills().containsAll(skills)).collect(Collectors.toList());
  }
}
