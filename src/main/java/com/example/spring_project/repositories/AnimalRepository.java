package com.example.spring_project.repositories;

import com.example.spring_project.models.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends SoftDeleteRepository<Animal, Long> {
    List<Animal> findByDeletedAtIsNull();
    Optional<Animal> findByIdAndDeletedAtIsNull(Long id);
}
