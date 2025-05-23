package com.example.demo.Common.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
