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
- **Code Simplification**: Lombok

## Architecture
- **Model Layer**: Weather entity with JPA annotations
- **Repository Layer**: Spring Data JPA repository with custom queries
- **Service Layer**: Business logic for data processing and retrieval
- **Controller Layer**: REST endpoints for API access

## Setup

### Prerequisites
- Java 17+
- Maven 3.6+ (or use included Maven wrapper)
- MySQL 8.0+

### Database Setup
```sql
CREATE DATABASE weatherdb;
```

### Configuration
Update `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

**Note:** The `testset.csv` file is included in the project root.

### Run
**Windows:**
```bash
mvnw.cmd spring-boot:run
```

**Mac/Linux:**
```bash
./mvnw spring-boot:run
```

**Or with Maven installed:**
```bash
mvn spring-boot:run
```

Application starts at: **http://localhost:8080**

## API Endpoints

### 1. Home
```
GET http://localhost:8080/
```
Returns available endpoints and API documentation

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
- ✅ Automatic CSV data import on startup
- ✅ MySQL database storage with JPA
- ✅ Modular 3-layer architecture
- ✅ RESTful API design
- ✅ Temperature statistics (max, min, median)
- ✅ Date and month-based filtering
- ✅ Maven wrapper included (no Maven installation required)
- ✅ Lombok for clean code

## CSV File Format
The CSV file should have the following columns:
```
datetime_utc, _conds, _dewptm, _fog, _hail, _heatindexm, _hum, _precipm, _pressurem, _rain, _snow, _tempm, _thunder, _tornado, _vism, _wdird, _wdire, _wgustm, _windchillm, _wspdm
```

Example data:
```
19961101-11:00,Smoke,9,0,0,,27,,1010,0,0,30,0,0,5,280,West,,,7.4
19961101-12:00,Smoke,10,0,0,,32,,-9999,0,0,28,0,0,,0,North,,,
```

## Dependencies
```xml
<dependencies>
    <!-- Spring Boot Web for REST API -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA for database access -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- MySQL Connector -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>
    
    <!-- Lombok for reducing boilerplate -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    
    <!-- OpenCSV for CSV parsing -->
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.9</version>
    </dependency>
</dependencies>
```

## How It Works

### Application Startup
1. **WeatherApplication** starts Spring Boot
2. Connects to MySQL database
3. **CsvProcessorService** runs automatically
4. Checks if data exists in database
5. If empty, loads data from `testset.csv`
6. Parses CSV and saves to database
7. API ready to accept requests

### Data Flow
```
HTTP Request → Controller → Service → Repository → Database
                    ↓
              JSON Response
```

## Troubleshooting

### Error: "Java not found"
**Solution:** Install Java 17+ and add to PATH
```bash
java -version  # Should show 17 or higher
```

### Error: "Access denied for user 'root'"
**Solution:** Update MySQL password in `application.properties`
```properties
spring.datasource.password=YOUR_ACTUAL_PASSWORD
```

### Error: "Unknown database 'weatherdb'"
**Solution:** Create database in MySQL
```sql
CREATE DATABASE weatherdb;
```

### Error: "Port 8080 already in use"
**Solution:** Change port in `application.properties`
```properties
server.port=8081
```

### Error: "testset.csv not found"
**Solution:** Ensure CSV file is in project root (same folder as pom.xml)

## Development

### Running in IDE
1. Import as Maven project
2. Update `application.properties` with MySQL credentials
3. Run `WeatherApplication.java`

### Building JAR
```bash
mvnw.cmd clean package
java -jar target/weather-api-1.0.0.jar
```

## API Testing Examples

### Using cURL
```bash
# Get November data
curl http://localhost:8080/api/weather/month/11

# Get specific date
curl "http://localhost:8080/api/weather/date?date=1996-11-01T11:00:00"

# Get monthly stats
curl "http://localhost:8080/api/weather/stats/monthly?year=1996&month=11"

# Get yearly stats
curl http://localhost:8080/api/weather/stats/yearly/1996
```

### Using Browser
Simply paste the URLs in your browser:
- http://localhost:8080/
- http://localhost:8080/api/weather/month/11
- http://localhost:8080/api/weather/stats/monthly?year=1996&month=11

## License
This project is open source and available for educational purposes.

## Author
Developed as a weather data analysis REST API using Spring Boot and MySQL.

---

**Note:** First run takes 1-2 minutes to load CSV data. Subsequent runs are instant as data is already in the database.
