package com.example.spring_project.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnimalEventHandler {

    @KafkaListener(topics = "animal-events", groupId = "animal-service")
    public void handleUserEvent(String animalName) {
        log.info("Received animal event for animal: {}", animalName);
    }
}
