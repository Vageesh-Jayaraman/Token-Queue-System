package com.example.consumer_display.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumeService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    SSEService sseService;

    @KafkaListener(topics = "token-events", groupId = "display-group")
    public void consume(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);

            Long token = jsonNode.get("tokenNo").asLong();
            String departmentId = jsonNode.get("departmentId").asText();
            String eventType = jsonNode.get("eventType").asText();
            String payload = String.format("{\"event\":\"%s\", \"departmentId\":\"%s\", \"tokenNo\":%d}",
                    eventType, departmentId, token);

            sseService.send(payload);

        } catch (Exception e) {
            System.err.println("Failed to parse JSON message: " + message);
            e.printStackTrace();
        }
    }
}
