package com.patrick.calllogscorrelationtask;

import com.patrick.calllogscorrelationtask.models.CallLog;
import com.patrick.calllogscorrelationtask.service.CallLogAnalyzer;
import com.patrick.calllogscorrelationtask.utils.CallLogFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CallLogsCorrelationTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallLogsCorrelationTaskApplication.class, args);

        try {
            CallLogFileReader reader = new CallLogFileReader();
            List<CallLog> callLogs = reader.readCallLogs("data/call_logs.csv");

            CallLogAnalyzer analyzer = new CallLogAnalyzer();
            analyzer.identifyTrendsAndPatterns(callLogs);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
