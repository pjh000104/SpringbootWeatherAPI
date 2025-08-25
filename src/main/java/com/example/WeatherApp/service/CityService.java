package com.example.WeatherApp.service;

import com.example.WeatherApp.model.City;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final RedisTemplate<String, City> redisTemplate;

    public CityService(RedisTemplate<String, City> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveCities(List<City> cities) {
        for (City city : cities) {
            redisTemplate.opsForValue().set(city.getName(), city);
        }
    }

    public City getCity(String name) {
        return redisTemplate.opsForValue().get(name);
    }
}
