package com.example.tokenqueue.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.tokenqueue.dtos.TokenEventDTO;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void storeValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Long getNextToken(String departmentId){
        String key = departmentId + "_counter";
        return redisTemplate.opsForValue().increment(key);
    }

    public void addToQueue(TokenEventDTO token){
        String department = token.getDepartmentId();
        Long tokenId = token.getTokenId();
        Long userId = token.getUserId();
        Long tokenNo = token.getTokenNo();
        String key = "queue:" + department;
        String value = userId + ":" + tokenId + ":" + tokenNo;
        redisTemplate.opsForList().rightPush(key, value);
    }
}
