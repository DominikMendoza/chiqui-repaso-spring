package com.example.spring_project.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnimalResponseDTO {
    private Long id;
    private String name;
    private String type;
}
