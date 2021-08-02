package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and
 * customer controllers would be fine too, though that is not part of the required scope for this
 * class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private CustomerService customerService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private PetService petService;

  @PostMapping("/customer")
  public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDto) {
    return convertToDto(customerService.save(convertFromDto(customerDto)));
  }

  @GetMapping("/customer")
  public List<CustomerDTO> getAllCustomers() {
    return customerService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/customer/pet/{petId}")
  public CustomerDTO getOwnerByPet(@PathVariable long petId) {
    Customer pet = petService.getOwnerForPet(petId);
    
    if (pet == null) return null;

    return convertToDto(pet);
  }

  @PostMapping("/employee")
  public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDto) {
    return convertToDto(employeeService.save(convertFromDto(employeeDto)));
  }

  @GetMapping("/employee/{employeeId}")
  public EmployeeDTO getEmployee(@PathVariable long employeeId) {
    return convertToDto(employeeService.getById(employeeId));
  }

  @PutMapping("/employee/{employeeId}")
  public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable,
      @PathVariable long employeeId) {
    employeeService.setAvailabilityForEmployee(employeeId, daysAvailable);
  }

  @GetMapping("/employee/availability")
  public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDto) {
    return employeeService.getAvailableEmployees(employeeDto.getSkills(), employeeDto.getDate())
        .stream().map(this::convertToDto).collect(Collectors.toList());
  }

  private CustomerDTO convertToDto(Customer customer) {
    CustomerDTO customerDto = new CustomerDTO();
    customerDto.setId(customer.getId());
    customerDto.setName(customer.getName());
    customerDto.setPhoneNumber(customer.getPhoneNumber());
    customerDto.setNotes(customer.getNotes());
    customerDto.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));

    return customerDto;
  }

  private Customer convertFromDto(CustomerDTO customerDto) {
    Customer customer = new Customer();
    customer.setId(customerDto.getId());
    customer.setName(customerDto.getName());
    customer.setPhoneNumber(customerDto.getPhoneNumber());
    customer.setNotes(customerDto.getNotes());

    if (customerDto.getPetIds() == null) {
      customer.setPets(Collections.emptyList());
    } else {
      customer.setPets(
          customerDto.getPetIds().stream().map(petService::getById).collect(Collectors.toList()));
    }

    return customer;
  }

  private EmployeeDTO convertToDto(Employee employee) {
    EmployeeDTO employeeDto = new EmployeeDTO();
    employeeDto.setId(employee.getId());
    employeeDto.setName(employee.getName());
    employeeDto.setSkills(employee.getSkills());
    employeeDto.setDaysAvailable(employee.getDaysAvailable());

    return employeeDto;
  }

  private Employee convertFromDto(EmployeeDTO employeeDto) {
    Employee employee = new Employee();
    employee.setId(employeeDto.getId());
    employee.setName(employeeDto.getName());
    employee.setSkills(employeeDto.getSkills());
    employee.setDaysAvailable(employeeDto.getDaysAvailable());

    return employee;
  }
}
