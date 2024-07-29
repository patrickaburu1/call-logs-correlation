package com.patrick.calllogscorrelationtask.utils;

import com.patrick.calllogscorrelationtask.models.CallLog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class CallLogFileReader {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<CallLog> readCallLogs(String filePath) throws IOException {
        List<CallLog> callLogs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip the header
            while ((line = br.readLine()) != null) {
                callLogs.add(parseCallLog(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            throw e;
        }
        return callLogs;
    }

    private CallLog parseCallLog(String line) {
        String[] fields = line.split(",");
        CallLog log = new CallLog();
        log.setCallStartTime(LocalDateTime.parse(fields[0], formatter));
        log.setCallEndTime(LocalDateTime.parse(fields[1], formatter));
        log.setAgentId(fields[2]);
        log.setCallerId(fields[3]);
        log.setCallDuration(Integer.parseInt(fields[4]));
        log.setCallOutcome(fields[5]);
        log.setCustomerSatisfaction(Integer.parseInt(fields[6]));
        return log;
    }


}
