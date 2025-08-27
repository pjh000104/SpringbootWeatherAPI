package com.example.WeatherApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.WeatherApp.model.Cities;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;


@Service
public class WeatherService {

    private final RedisTemplate<String, String> redisTemplate; // store JSON as String
    private final RestTemplate restTemplate;
    @Value("${spring.weatherAPIKEY}")
    private String apikey;

    public WeatherService(@Qualifier("myStringRedisTemplate") RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.restTemplate = new RestTemplate();
    }

    public String getWeather(String location, String date1, String date2, String unitGroup, String include) {
        StringBuilder keyBuilder = new StringBuilder(location);
        if (date1 != null && !date1.isEmpty()) keyBuilder.append("-").append(date1);
        if (date2 != null && !date2.isEmpty()) keyBuilder.append("-").append(date2);
        if (unitGroup != null && !unitGroup.isEmpty()) keyBuilder.append("-").append(unitGroup);
        if (include != null && !include.isEmpty()) keyBuilder.append("-").append(include);
        // Create a unique key for caching
        String cacheKey = keyBuilder.toString();
        System.out.println("Caching key: " + cacheKey);
        // Check Redis cache
        String cachedResult = redisTemplate.opsForValue().get(cacheKey);
        if (cachedResult != null) {
            System.err.println("From Redis cache: " + cachedResult);
            return cachedResult;
        }

        // Call the API
        String apiResult = callWeatherApi(location, date1, date2, unitGroup, include);

        // Store result in Redis
        redisTemplate.opsForValue().set(cacheKey, apiResult);

        return apiResult;
    }

    private String callWeatherApi(String location, String date1, String date2, String unitGroup, String include) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + location)
                .queryParam("key", apikey);

        if (date1 != null && !date1.isEmpty()) builder.queryParam("date1", date1);
        if (date2 != null && !date2.isEmpty()) builder.queryParam("date2", date2);
        if (unitGroup != null && !unitGroup.isEmpty()) builder.queryParam("unitGroup", unitGroup);
        if (include != null && !include.isEmpty()) builder.queryParam("include", include);

        builder.queryParam("contentType", "json"); // always return JSON
        String url = builder.toUriString();
        return restTemplate.getForObject(url, String.class);
    }
}
