package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.jdnd.course3.critter.pet.Pet;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customers;

  public Customer save(Customer customer) {
    return customers.save(customer);
  }
  
  public List<Customer> getAll() {
    return customers.findAll();
  }

  public Customer getById(Long customerId) {
    return customers.findById(customerId).orElse(null);
  }

  public List<Pet> getPetsByOwnerId(Long customerId) {
    return customers.findById(customerId).get().getPets();
  }
}
