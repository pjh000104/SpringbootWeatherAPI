package com.example.WeatherApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.WeatherApp.model.City;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
        private String redishost;
    @Value("${spring.redis.password}")
        private String password;
    @Value("${spring.redis.username}")
        private String username;
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        
        config.setHostName(redishost);
        config.setPort(18981);
        config.setPassword(password);
        config.setUsername(username);


        return new LettuceConnectionFactory(config);
    }

    // For String caching (like WeatherService)
    @Bean
    public RedisTemplate<String, String> myStringRedisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }

    // For City objects
    @Bean
    public RedisTemplate<String, City> cityRedisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, City> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
