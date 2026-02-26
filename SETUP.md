# Weather API - Setup Instructions

## Prerequisites
- **Java 17+** - https://www.oracle.com/java/technologies/downloads/
- **MySQL 8.0+** - https://dev.mysql.com/downloads/installer/
- **Maven** (or use included mvnw)

## Setup Steps

### 1. Install Java & MySQL
- Install Java 17 JDK
- Install MySQL Server (remember the root password)

### 2. Create Database
Open MySQL and run:
```sql
CREATE DATABASE weatherdb;
```

### 3. Configure Database Password
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```
Replace with your actual MySQL password.

### 4. Add CSV File
**IMPORTANT:** Place `testset.csv` file in the project root folder (same level as pom.xml)

### 5. Run Application

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

## Test the API
- http://localhost:8080/
- http://localhost:8080/api/weather/month/11
- http://localhost:8080/api/weather/stats/monthly?year=1996&month=11

## Troubleshooting

**"testset.csv not found"**
- Place CSV file in project root (same folder as pom.xml)

**"Access denied for user 'root'"**
- Update password in application.properties

**"Unknown database 'weatherdb'"**
- Run: `CREATE DATABASE weatherdb;` in MySQL

**"Port 8080 already in use"**
- Change port in application.properties: `server.port=8081`

## Project Structure
```
weather-api/
├── src/main/java/com/weather/
│   ├── WeatherApplication.java
│   ├── controller/
│   ├── model/
│   ├── repository/
│   └── service/
├── src/main/resources/
│   └── application.properties
├── pom.xml
└── testset.csv (PLACE HERE)
```
