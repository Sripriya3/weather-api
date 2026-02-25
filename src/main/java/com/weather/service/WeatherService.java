package com.weather.service;

import com.weather.model.Weather;
import com.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository repository;
    
    public List<Weather> getWeatherByMonth(Integer month) {
        return repository.findAll().stream()
            .filter(w -> w.getMonth() != null && w.getMonth().equals(month))
            .toList();
    }
    
    public List<Weather> getWeatherByDate(LocalDateTime date) {
        LocalDateTime start = date.withHour(0).withMinute(0);
        LocalDateTime end = date.withHour(23).withMinute(59);
        return repository.findByDatetimeUtcBetween(start, end);
    }
    
    public Map<String, Double> getMonthlyTempStats(Integer year, Integer month) {
        Map<String, Double> stats = new HashMap<>();
        stats.put("max", repository.findMaxTempByYearAndMonth(year, month));
        stats.put("min", repository.findMinTempByYearAndMonth(year, month));
        stats.put("median", repository.findMedianTempByYearAndMonth(year, month));
        return stats;
    }
    
    public List<Map<String, Object>> getYearlyMonthlyStats(Integer year) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            Map<String, Object> monthData = new HashMap<>();
            monthData.put("month", month);
            monthData.put("stats", getMonthlyTempStats(year, month));
            result.add(monthData);
        }
        return result;
    }
}
