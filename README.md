# Weather API - Delhi Weather Data Analysis

![Tech Stack](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-green?style=for-the-badge&logo=springboot) ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-3.6+-red?style=for-the-badge&logo=apachemaven)

## Project Overview
Spring Boot REST API for analyzing 20 years of Delhi weather data including temperature, humidity, pressure, and weather conditions.

## Technology Stack
```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│    REST API (Spring Web MVC)            │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│          Service Layer                  │
│   Business Logic (Java)                 │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│        Repository Layer                 │
│   Spring Data JPA (Java)                │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│         Database Layer                  │
│          MySQL 8.0                      │
└─────────────────────────────────────────┘
```

## Languages & Frameworks
- **Backend**: Java 17 (100%)
- **Framework**: Spring Boot 3.2.0
- **Database**: MySQL 8.0
- **Build Tool**: Maven
- **Data Processing**: OpenCSV

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

**Note:** Place the `testset.csv` file in your project root or specify the full path in the configuration.

### CSV File Format
The CSV file should have the following columns:
```
datetime_utc, _conds, _dewptm, _fog, _hail, _heatindexm, _hum, _precipm, _pressurem, _rain, _snow, _tempm, _thunder, _tornado, _vism, _wdird, _wdire, _wgustm, _windchillm, _wspdm
```

Example data:
```
19961101-11:00,Smoke,9,0,0,,27,,1010,0,0,30,0,0,5,280,West,,,7.4
19961101-12:00,Smoke,10,0,0,,32,,-9999,0,0,28,0,0,,0,North,,,
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

### 6. Filter by Year, Month, Day (NEW)
```
GET http://localhost:8080/api/weather/filter?year={year}&month={month}&day={day}
```
All parameters are optional. Examples:
- `/api/weather/filter?year=2017` - All data for 2017
- `/api/weather/filter?month=4` - All April data across all years
- `/api/weather/filter?year=2017&month=4` - April 2017 data
- `/api/weather/filter?year=2017&month=4&day=15` - April 15, 2017 data

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
