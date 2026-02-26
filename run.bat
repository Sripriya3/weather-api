@echo off
echo ====================================
echo   Weather API - Spring Boot
echo ====================================
echo.

echo Checking Java...
java -version
if errorlevel 1 (
    echo ERROR: Java not found. Install Java 17+
    pause
    exit /b 1
)

echo.
echo Starting application...
echo API will be available at: http://localhost:8080
echo.

mvnw.cmd spring-boot:run

pause
