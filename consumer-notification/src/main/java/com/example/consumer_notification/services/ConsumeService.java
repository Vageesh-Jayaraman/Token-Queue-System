package com.example.consumer_notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.consumer_notification.dtos.TokenEventDTO;

@Service
public class ConsumeService {

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "token-events", groupId = "notification-group")
    public void consume(TokenEventDTO event) {
        System.out.println("Received Token Event: " + event);
        TokenEventDTO receivedToken = event;
        sendEmail(receivedToken.getEmail(), receivedToken.getTokenNo(), receivedToken.getDepartmentId(), receivedToken.getEventType());
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

