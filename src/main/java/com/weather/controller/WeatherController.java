package com.weather.controller;

import com.weather.model.Weather;
import com.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService service;
    
    @GetMapping("/month/{month}")
    public List<Weather> getByMonth(@PathVariable Integer month) {
        return service.getWeatherByMonth(month);
    }
    
    @GetMapping("/date")
    public List<Weather> getByDate(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return service.getWeatherByDate(date);
    }
    
    @GetMapping("/stats/monthly")
    public Map<String, Double> getMonthlyStats(
        @RequestParam Integer year, 
        @RequestParam Integer month) {
        return service.getMonthlyTempStats(year, month);
    }
    
    @GetMapping("/stats/yearly/{year}")
    public List<Map<String, Object>> getYearlyStats(@PathVariable Integer year) {
        return service.getYearlyMonthlyStats(year);
    }
}
