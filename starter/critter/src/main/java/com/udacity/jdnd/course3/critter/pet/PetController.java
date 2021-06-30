package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.udacity.jdnd.course3.critter.schedule.ScheduleService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
  @Autowired
  private PetService petService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private ScheduleService scheduleService;

  @PostMapping
  public PetDTO savePet(@RequestBody PetDTO petDto) {
    return convertToDto(petService.save(convertFromDto(petDto)));
  }

  @GetMapping("/{petId}")
  public PetDTO getPet(@PathVariable long petId) {
    return convertToDto(petService.getById(petId));
  }

  @GetMapping
  public List<PetDTO> getPets() {
    return petService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/owner/{ownerId}")
  public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
    return customerService.getPetsByOwnerId(ownerId).stream().map(this::convertToDto)
        .collect(Collectors.toList());
  }

  private PetDTO convertToDto(Pet pet) {
    PetDTO petDto = new PetDTO();
    petDto.setId(pet.getId());
    petDto.setBirthDate(pet.getBirthDate());
    petDto.setName(pet.getName());

    if (pet.getOwner() != null) {
      petDto.setOwnerId(pet.getOwner().getId());
    }

    petDto.setType(pet.getType());
    petDto.setNotes(pet.getNotes());

    return petDto;
  }

  private Pet convertFromDto(PetDTO petDto) {
    Pet pet = new Pet();
    pet.setId(petDto.getId());
    pet.setType(petDto.getType());
    pet.setName(petDto.getName());

    Customer owner = customerService.getById(petDto.getOwnerId());
    pet.setOwner(owner);

    pet.setBirthDate(petDto.getBirthDate());
    pet.setNotes(petDto.getNotes());
    pet.setSchedules(scheduleService.getAllForPet(pet));
    return pet;
  }
}
