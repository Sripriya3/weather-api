# Weather API - Delhi Weather Data Analysis

## Project Overview
Spring Boot REST API for analyzing 20 years of Delhi weather data including temperature, humidity, pressure, and weather conditions.

## Architecture
- **Model Layer**: Weather entity with JPA annotations
- **Repository Layer**: Spring Data JPA repository with custom queries
- **Service Layer**: Business logic for data processing and retrieval
- **Controller Layer**: REST endpoints for API access

## Setup

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### Database Setup
```sql
CREATE DATABASE weatherdb;
```

### Configuration
Update `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
csv.file.path=path/to/testset.csv
```

### Run
```bash
mvn spring-boot:run
```

## API Endpoints

### 1. Home
```
GET http://localhost:8080/
```
Returns available endpoints

### 2. Get Weather by Month
```
GET http://localhost:8080/api/weather/month/{month}
```
Example: `/api/weather/month/11` - Returns all weather data for November across all years

### 3. Get Weather by Date
```
GET http://localhost:8080/api/weather/date?date={datetime}
```
Example: `/api/weather/date?date=1996-11-01T11:00:00`

### 4. Get Monthly Temperature Statistics
```
GET http://localhost:8080/api/weather/stats/monthly?year={year}&month={month}
```
Example: `/api/weather/stats/monthly?year=1996&month=11`

Returns:
```json
{
  "max": 31.0,
  "min": 15.0,
  "median": 23.5
}
```

### 5. Get Yearly Statistics (All Months)
```
GET http://localhost:8080/api/weather/stats/yearly/{year}
```
Example: `/api/weather/stats/yearly/1996`

Returns temperature stats for each month of the specified year.

## Project Structure
```
src/main/java/com/weather/
├── WeatherApplication.java          # Main application
├── controller/
│   ├── HomeController.java          # Root endpoint
│   └── WeatherController.java       # Weather API endpoints
├── model/
│   └── Weather.java                 # Entity model
├── repository/
│   └── WeatherRepository.java       # Data access layer
└── service/
    ├── CsvProcessorService.java     # CSV data loader
    └── WeatherService.java          # Business logic
```

## Features
- Automatic CSV data import on startup
- MySQL database storage
- Modular architecture
- RESTful API design
- Temperature statistics (max, min, median)
- Date and month-based filtering
