# Website Monitor with Multiple Comparison Strategies

This project extends the website monitoring system to support multiple strategies for comparing website content. The system maintains the same architecture as the previous version while adding new functionality for different types of content comparison.

## Features

1. Multiple Comparison Strategies:
   - Size Comparison: Compares the total size of the website content
   - HTML Content Comparison: Compares the exact HTML content using hash comparison
   - Text Content Comparison: Compares the extracted text content from HTML

2. Real-time Monitoring:
   - Monitors multiple websites simultaneously
   - Configurable check intervals
   - Response time tracking
   - Availability status

3. Notification System:
   - Multiple notification channels (Email, SMS, Console)
   - Configurable notification frequency
   - Customizable contact information

## How to Run

### Option 1: Using Maven (Recommended)

1. Make sure you have Java 17 and Maven installed
2. Navigate to the project directory
3. Build the project:
   ```bash
   mvn clean package
   ```
4. Run the application:
   ```bash
   java -jar target/website-monitor-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

### Option 2: Manual Compilation

1. Make sure you have Java 17 installed
2. Navigate to the project directory
3. Run the compilation script:
   ```bash
   compile.bat
   ```
4. Run the application:
   ```bash
   run.bat
   ```

Or manually:
```bash
# Compile
javac -d bin -cp "lib/*" src/website/monitor/*.java src/website/monitor/strategies/*.java

# Create JAR
jar cfm website-monitor.jar manifest.txt -C bin .

# Run
java -cp "website-monitor.jar;lib/*" website.monitor.Main
```

## Usage

1. Set up notification preferences:
   - Choose notification channel (Email, SMS, or Console)
   - Select notification frequency (Daily, Weekly, or Monthly)
   - Enter contact information

2. Monitor websites:
   - The system starts monitoring three example websites with different comparison strategies
   - You can subscribe to additional websites
   - Change comparison strategies for any monitored website
   - Check subscription status
   - Update notification preferences

## Architecture

The project follows the Observer pattern and Strategy pattern:
- Observer Pattern: For handling notifications and updates
- Strategy Pattern: For implementing different content comparison strategies

## Dependencies

- Java 17
- JSoup (for HTML parsing) 