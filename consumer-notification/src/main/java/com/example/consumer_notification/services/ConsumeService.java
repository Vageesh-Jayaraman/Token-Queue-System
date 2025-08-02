package com.example.consumer_notification.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ConsumeService {

    @Autowired
    private JavaMailSender mailSender;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "token-events", groupId = "notification-group")
    public void consume(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);

            String email = jsonNode.get("email").asText();
            Long token = jsonNode.get("tokenNo").asLong();
            String departmentId = jsonNode.get("departmentId").asText();
            String eventType = jsonNode.get("eventType").asText();
            sendEmail(email, token, departmentId, eventType);
        } catch (Exception e) {
            System.err.println("Failed to parse JSON message: " + message);
            e.printStackTrace();
        }
    }

    public void sendEmail(String emailId, Long token, String departmentId, String type){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jvageesh.dev@gmail.com");
        message.setTo(emailId);
        message.setSubject("Token Event Notification");
        message.setText("Event: " + type + "\nToken: " + token + "\nDepartment: " + departmentId);
        mailSender.send(message);
    }
}
