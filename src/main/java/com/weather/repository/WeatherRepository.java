package com.weather.repository;

import com.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    List<Weather> findByYearAndMonth(Integer year, Integer month);
    List<Weather> findByDatetimeUtcBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT MAX(w.temperature) FROM Weather w WHERE w.year = ?1 AND w.month = ?2")
    Double findMaxTempByYearAndMonth(Integer year, Integer month);
    
    @Query("SELECT MIN(w.temperature) FROM Weather w WHERE w.year = ?1 AND w.month = ?2")
    Double findMinTempByYearAndMonth(Integer year, Integer month);
    
    @Query("SELECT AVG(w.temperature) FROM Weather w WHERE w.year = ?1 AND w.month = ?2")
    Double findMedianTempByYearAndMonth(Integer year, Integer month);
}
