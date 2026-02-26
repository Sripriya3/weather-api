# Weather API - Quick Setup

## Requirements
1. Java 17 or higher
2. MySQL 8.0 or higher

## Setup (5 minutes)

### Step 1: Install Java & MySQL
- Java: https://www.oracle.com/java/technologies/downloads/
- MySQL: https://dev.mysql.com/downloads/installer/

### Step 2: Create Database
Open MySQL and run:
```sql
CREATE DATABASE weatherdb;
```

### Step 3: Configure Password
Edit `src/main/resources/application.properties`
Change this line to your MySQL password:
```
spring.datasource.password=root
```

### Step 4: Run
Double-click `RUN.bat`

Or manually:
```bash
mvnw.cmd spring-boot:run
```

## Test
Open browser: http://localhost:8080

## API Endpoints
- http://localhost:8080/api/weather/month/11
- http://localhost:8080/api/weather/stats/monthly?year=1996&month=11

## Files Included
✅ All Java source code
✅ testset.csv (weather data)
✅ Maven wrapper (no Maven installation needed)
✅ Configuration files

## Troubleshooting
- **Java not found**: Install Java 17+
- **MySQL error**: Check password in application.properties
- **Database error**: Run `CREATE DATABASE weatherdb;`
