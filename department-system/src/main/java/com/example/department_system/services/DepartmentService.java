package com.example.department_system.services;

import com.example.department_system.dtos.TokenEventDTO;
import com.example.department_system.repositories.TokenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    TokenRepository tokenRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public List<String> getAllTokens(String departmentId) {
        String queueKey = "queue:" + departmentId;
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.range(queueKey, 0, -1);
    }

    public String getTop(String departmentId) {
        String queueKey = "queue:" + departmentId;
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.index(queueKey, -1);
    }

    public String serveTop(String departmentId) {
        String queueKey = "queue:" + departmentId;
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        String token = listOps.rightPop(queueKey);

        if (token != null) {
            String[] parts = token.split(":");
            if (parts.length == 3) {
                Long tokenId = Long.parseLong(parts[1]);
                tokenRepository.markAsServed(tokenId);
                TokenEventDTO event = new TokenEventDTO("TOKEN_SERVED", tokenId);
                kafkaTemplate.send("token-events", event);
            }
        }

        return token;
    }
}
