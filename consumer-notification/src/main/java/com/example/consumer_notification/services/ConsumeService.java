package com.example.consumer_notification.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.consumer_notification.dtos.TokenEventDTO;

@Service
public class ConsumeService {
    
    @KafkaListener(topics = "token-events", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(TokenEventDTO event) {
        System.out.println("Received Token Event: " + event);
    }
}
