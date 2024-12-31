package com.example.spring_project.models;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditableEntityListener {
    @PrePersist
    public void setCreatedDate(Object entity) {
        if (entity instanceof Animal animal) {
            LocalDateTime now = LocalDateTime.now();
            animal.setCreatedAt(now);
            animal.setUpdatedAt(now);
        }
    }

    @PreUpdate
    public void setUpdatedDate(Object entity) {
        if (entity instanceof Animal animal) {
            animal.setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreRemove
    public void setDeletedDate(Object entity) {
        if (entity instanceof Animal animal) {
            animal.setDeletedAt(LocalDateTime.now());
        }
    }
}
