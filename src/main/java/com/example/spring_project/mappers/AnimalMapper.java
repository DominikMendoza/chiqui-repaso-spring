package com.example.spring_project.mappers;

import com.example.spring_project.dtos.AnimalResponseDTO;
import com.example.spring_project.models.Animal;

public class AnimalMapper {
    public static AnimalResponseDTO toResponseDTO(Animal animal) {
        return AnimalResponseDTO.builder()
                .id(animal.getId())
                .name(animal.getName())
                .type(animal.getType())
                .build();
    }
}
