package com.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Weather API");
        response.put("endpoints", Map.of(
            "Get by month", "/api/weather/month/{month}",
            "Get by date", "/api/weather/date?date=1996-11-01T11:00:00",
            "Monthly stats", "/api/weather/stats/monthly?year=1996&month=11",
            "Yearly stats", "/api/weather/stats/yearly/{year}"
        ));
        return response;
    }
}
