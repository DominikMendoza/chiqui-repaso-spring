package com.example.spring_project.services;

import com.example.spring_project.dtos.CreateAnimalDTO;
import com.example.spring_project.models.Animal;
import com.example.spring_project.repositories.AnimalRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Cacheable("animals")
    public List<Animal> getAllAnimals() {
        return animalRepository.findByDeletedAtIsNull();
    }

    @Cacheable(value = "animal", key = "#id")
    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    @CacheEvict(value = "animals", allEntries = true)
    public Animal createAnimal(CreateAnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setType(animalDTO.getType());
        animal.setName(animalDTO.getName());
        return animalRepository.save(animal);
    }

    @CacheEvict(value = "animal", key = "#id")
    public void deleteAnimal(Long id) {
        animalRepository.softDeleteById(id);
    }

    @CacheEvict(value = "animals", allEntries = true)
    public void clearCache() {
        System.out.println("Cache cleared!");
    }
}
