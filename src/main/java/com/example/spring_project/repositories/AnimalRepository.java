package com.example.spring_project.repositories;

import com.example.spring_project.models.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends SoftDeleteRepository<Animal, Long> {
    List<Animal> findByDeletedAtIsNull();

}
