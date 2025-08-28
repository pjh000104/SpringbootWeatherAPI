package com.example.WeatherApp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherApp.service.WeatherService;

@RestController
@CrossOrigin(origins = "https://parkparkweatherapp.netlify.app/")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(
            @RequestParam String location,
            @RequestParam(required = false) String date1,
            @RequestParam(required = false) String date2,
            @RequestParam(required = false) String unitGroup,
            @RequestParam(required = false) String include) {


        String result = weatherService.getWeather(location, date1, date2, unitGroup, include);


        return result; 
    }
}

