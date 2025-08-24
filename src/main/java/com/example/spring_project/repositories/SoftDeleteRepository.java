package com.example.spring_project.repositories;

import com.example.spring_project.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.Optional;

@NoRepositoryBean
public interface SoftDeleteRepository<T, K> extends JpaRepository<T, K> {

    default void softDelete(T entity) {
        if (entity instanceof Animal animal) {
            animal.setDeletedAt(LocalDateTime.now());
            save(entity);
        }
    }

    default void softDeleteById(K id) {
        Optional<T> entity = findById(id);
        entity.ifPresent(this::softDelete);
    }
}
