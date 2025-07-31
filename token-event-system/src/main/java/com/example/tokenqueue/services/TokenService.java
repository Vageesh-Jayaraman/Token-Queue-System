package com.example.tokenqueue.services;

import com.example.tokenqueue.dtos.TokenEventDTO;
import com.example.tokenqueue.models.DepartmentModel;
import com.example.tokenqueue.models.TokenModel;
import com.example.tokenqueue.models.UserModel;
import com.example.tokenqueue.repositories.DepartmentRepository;
import com.example.tokenqueue.repositories.TokenRepository;
import com.example.tokenqueue.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    private final TokenRepository tokenRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final RedisService redisService;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public TokenModel createToken(String departmentId, Long userId) {
        DepartmentModel department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<TokenModel> existingToken = tokenRepository.findByUserAndDepartment(user, department);
        if (existingToken.isPresent()) {
            return existingToken.get();
        }

        TokenModel token = new TokenModel();
        token.setDepartment(department);
        token.setUser(user);
        token.setCounter(redisService.getNextToken(departmentId));
        token.setStatus("PENDING");

        TokenModel savedToken = tokenRepository.save(token);

        TokenEventDTO event = new TokenEventDTO(
                "TOKEN_GENERATED",
                savedToken.getId(),
                departmentId,
                savedToken.getCounter(),
                userId,
                "PENDING",
                savedToken.getUser().getEmail(),
                Instant.now().toString());

        kafkaTemplate.send("token-events", event); 

        return savedToken;
    }

    public TokenModel getTokenByUser(Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return tokenRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Token not found for user"));
    }

    public TokenModel updateStatus(Long tokenId, String newStatus) {
        TokenModel token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        
        token.setStatus(newStatus);
        TokenModel updatedToken = tokenRepository.save(token);
    
        TokenEventDTO event = new TokenEventDTO(
            "TOKEN_UPDATED",
            updatedToken.getId(),
            updatedToken.getDepartment().getDepartment_id(),
            updatedToken.getCounter(),
            updatedToken.getUser().getUser_id(),
            updatedToken.getUser().getEmail(),
            newStatus,
            Instant.now().toString()
        );
    
        kafkaTemplate.send("token-events", event);
    
        return updatedToken;
    }
    
}
