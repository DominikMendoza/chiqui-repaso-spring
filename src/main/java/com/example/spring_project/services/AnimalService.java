package com.example.spring_project.services;

import com.example.spring_project.dtos.CreateAnimalDTO;
import com.example.spring_project.models.Animal;
import com.example.spring_project.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Cacheable(value = "animals", key = "'all'")
    public List<Animal> getAllAnimals() {
        log.debug("Fetching all animals from database");
        List<Animal> animals = animalRepository.findByDeletedAtIsNull();
        log.info("Retrieved {} animals", animals.size());
        return animals;
    }

    @Cacheable(value = "animals", key = "#id")
    public Optional<Animal> getAnimalById(Long id) {
        log.debug("Fetching animal with id: {}", id);
        Optional<Animal> animal = animalRepository.findByIdAndDeletedAtIsNull(id);
        if (animal.isPresent()) {
            log.info("Found animal: {}", animal.get().getName());
        } else {
            log.warn("Animal with id {} not found", id);
        }
        return animal;
    }

    @CacheEvict(value = "animals", allEntries = true)
    public Animal createAnimal(CreateAnimalDTO animalDTO) {
        log.info("Creating new animal: {} of type {}", animalDTO.getName(), animalDTO.getType());
        Animal animal = new Animal();
        animal.setType(animalDTO.getType());
        animal.setName(animalDTO.getName());
        Animal savedAnimal = animalRepository.save(animal);
        log.info("Successfully created animal with id: {}", savedAnimal.getId());
        return savedAnimal;
    }

    @CacheEvict(value = "animals", allEntries = true)
    public void deleteAnimal(Long id) {
        log.warn("Soft deleting animal with id: {}", id);
        animalRepository.softDeleteById(id);
        log.info("Successfully deleted animal with id: {}", id);
    }

    @CacheEvict(value = "animals", allEntries = true)
    public void clearCache() {
        log.info("Cache cleared successfully!");
    }
}
