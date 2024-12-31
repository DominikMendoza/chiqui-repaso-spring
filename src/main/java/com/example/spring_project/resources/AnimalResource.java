package com.example.spring_project.resources;

import com.example.spring_project.models.Animal;
import com.example.spring_project.repositories.AnimalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalResource {
    private final AnimalRepository animalRepository;

    public AnimalResource(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public List<String> getAnimals() {
        return Arrays.asList("Dog", "Cat", "Elephant", "Tiger");
    }

    @GetMapping("/memory")
    public List<Animal> getMemoryAnimals() {
        return animalRepository.findByDeletedAtIsNull();
    }

    @PostMapping("/memory")
    public void postMemoryAnimal() {
        animalRepository.save(new Animal());
    }

    @DeleteMapping("/memory")
    public void deleteMemoryAnimal() {
        animalRepository.softDeleteById(animalRepository.findByDeletedAtIsNull().stream().findFirst().get().getId());
    }
}
