package com.example.WeatherApp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherApp.service.WeatherService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
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

        // Call the service, which will check Redis first
        String result = weatherService.getWeather(location, date1, date2, unitGroup, include);

        // Optional: log to console
        // System.err.println(result);

        return result;  // Returns JSON from API or cached version
    }
}

