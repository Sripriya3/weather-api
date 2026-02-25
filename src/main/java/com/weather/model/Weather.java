package com.weather.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime datetimeUtc;
    @Column(name = "`condition`")
    private String condition;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Integer year;
    private Integer month;
    private Integer day;
}
