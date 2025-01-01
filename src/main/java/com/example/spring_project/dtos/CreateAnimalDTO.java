package com.example.spring_project.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnimalDTO {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 20)
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 4, max = 20)
    private String type;
}
