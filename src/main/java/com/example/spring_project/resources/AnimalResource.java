package com.example.spring_project.resources;

import com.example.spring_project.dtos.AnimalResponseDTO;
import com.example.spring_project.dtos.CreateAnimalDTO;
import com.example.spring_project.mappers.AnimalMapper;
import com.example.spring_project.models.Animal;
import com.example.spring_project.resources.responses.ApiResponse;
import com.example.spring_project.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalResource {
    private final AnimalService animalService;

    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AnimalResponseDTO>>> getAllAnimals() {
        List<AnimalResponseDTO> animals = animalService.getAllAnimals()
                .stream()
                .map(AnimalMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.OK.value(), animals, null)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnimalResponseDTO>> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id).orElse(null);
        if (animal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), null, "Animal not found"));
        }
        return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.OK.value(), AnimalMapper.toResponseDTO(animal), null)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AnimalResponseDTO>> createAnimal(@Valid @RequestBody CreateAnimalDTO dto) {
        Animal createdAnimal = animalService.createAnimal(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), AnimalMapper.toResponseDTO(createdAnimal), null));
    }
}
