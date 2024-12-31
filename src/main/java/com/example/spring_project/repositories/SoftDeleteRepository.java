package com.example.spring_project.repositories;

import com.example.spring_project.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.Optional;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends JpaRepository<T, ID> {

    default void softDelete(T entity) {
        if (entity instanceof Animal) {
            ((Animal) entity).setDeletedAt(LocalDateTime.now());
            save(entity);
        }
    }

    default void softDeleteById(ID id) {
        Optional<T> entity = findById(id);
        entity.ifPresent(this::softDelete);
    }
}
