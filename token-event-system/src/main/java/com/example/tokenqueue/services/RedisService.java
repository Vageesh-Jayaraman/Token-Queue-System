package com.example.tokenqueue.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
}
