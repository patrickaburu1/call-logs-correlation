# Call Logs Correlation Task

## Overview

This project analyzes call logs from a call center  to provide insights into various metrics such as:
- Call volume and time of day.
- Call duration and outcome (e.g., successful resolution vs. escalation).
- Agent performance (e.g., average call duration, number of calls handled).
- Customer satisfaction by agent.

The project is implemented in Java using Spring Boot for application setup and Gradle for build management.

## Project Structure

- `src/main/java/com/patrick/calllogscorrelationtask/`
    - `CallLogsCorrelationTaskApplication.java`: The main application class that runs the Spring Boot application.
    - `models/CallLog.java`: Model class representing the call log data.
    - `service/CallLogAnalyzer.java`: Service class containing methods to analyze the call logs.
    - `utils/CallLogFileReader.java`: Utility class for reading call log data from a CSV file.

- `src/test/java/com/patrick/calllogscorrelationtask/service/`
    - `CallLogAnalyzerTest.java`: Unit tests for `CallLogAnalyzer`.

## Dependencies

- Spring Boot
- Gradle

## Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   
2. **Build project**
    ```bash
   ./gradlew build
3. **Run the application**
    ```bash
    ./gradlew bootRun
4. **Run Unit test**
   ```bash
   ./gradlew test


## Output
 **Upon running the application, you will get a series of console outputs including:**

 * Call volume by time of day.
 * Call duration by outcome.
 * Agent performance (average call duration).  
 * Customer satisfaction by agent. 
 * Detailed report of call duration and counts by agent.