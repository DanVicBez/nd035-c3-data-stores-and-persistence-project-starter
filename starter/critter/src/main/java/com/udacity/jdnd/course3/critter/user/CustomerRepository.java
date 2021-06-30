package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.udacity.jdnd.course3.critter.pet.Pet;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
