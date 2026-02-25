package com.weather.service;

import com.opencsv.CSVReader;
import com.weather.model.Weather;
import com.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CsvProcessorService implements CommandLineRunner {
    private final WeatherRepository repository;
    
    @Value("${csv.file.path}")
    private String csvFilePath;
    
    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            System.out.println("Data already loaded. Skipping CSV import.");
            return;
        }
        
        System.out.println("Loading CSV data from: " + csvFilePath);
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            reader.skip(1);
            String[] line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");
            int count = 0;
            
            while ((line = reader.readNext()) != null) {
                if (line.length < 12) continue;
                try {
                    Weather weather = new Weather();
                    LocalDateTime dt = LocalDateTime.parse(line[0].trim(), formatter);
                    weather.setDatetimeUtc(dt);
                    weather.setCondition(line[1].trim());
                    weather.setTemperature(parseDouble(line[11]));
                    weather.setHumidity(parseDouble(line[6]));
                    weather.setPressure(parseDouble(line[8]));
                    weather.setYear(dt.getYear());
                    weather.setMonth(dt.getMonthValue());
                    weather.setDay(dt.getDayOfMonth());
                    repository.save(weather);
                    count++;
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + String.join(",", line));
                }
            }
            System.out.println("Loaded " + count + " weather records.");
        }
    }
    
    private Double parseDouble(String value) {
        try {
            double val = Double.parseDouble(value.trim());
            return val == -9999 ? null : val;
        } catch (Exception e) {
            return null;
        }
    }
}
