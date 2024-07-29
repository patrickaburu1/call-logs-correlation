package com.patrick.calllogscorrelationtask.service;

import com.patrick.calllogscorrelationtask.models.CallLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CallLogAnalyzer {
    public Map<String, Integer> analyzeCallDurations(List<CallLog> callLogs) {
        return callLogs.stream()
                .collect(Collectors.groupingBy(CallLog::getAgentId, Collectors.summingInt(CallLog::getCallDuration)));
    }

    public Map<String, Integer> analyzeCallOutcomes(List<CallLog> callLogs) {
        return callLogs.stream()
                .collect(Collectors.groupingBy(CallLog::getCallOutcome, Collectors.reducing(0, e -> 1, Integer::sum)));
    }

    public Map<Integer, Long> analyzeCallVolumeByTimeOfDay(List<CallLog> callLogs) {
        return callLogs.stream()
                .collect(Collectors.groupingBy(log -> log.getCallStartTime().getHour(), Collectors.counting()));
    }

    public Map<String, Double> analyzeCallDurationByOutcome(List<CallLog> callLogs) {
        return callLogs.stream()
                .collect(Collectors.groupingBy(CallLog::getCallOutcome, Collectors.averagingInt(CallLog::getCallDuration)));
    }

    public Map<String, Double> analyzeAgentPerformance(List<CallLog> callLogs) {
        return callLogs.stream()
                .collect(Collectors.groupingBy(CallLog::getAgentId, Collectors.averagingInt(CallLog::getCallDuration)));
    }

    public Map<String, Double> analyzeCustomerSatisfactionByAgent(List<CallLog> callLogs) {
        return callLogs.stream()
                .collect(Collectors.groupingBy(CallLog::getAgentId, Collectors.averagingDouble(CallLog::getCustomerSatisfaction)));
    }

    public void generateCallDurationReport(Map<String, Integer> agentCallDurations, Map<String, Integer> agentCallCounts) {
        System.out.println("Call Duration Report:");
        for (Map.Entry<String, Integer> entry : agentCallDurations.entrySet()) {
            String agentId = entry.getKey();
            Integer totalDuration = entry.getValue();
            Integer callCount = agentCallCounts.getOrDefault(agentId, 0);
            double averageDuration = callCount > 0 ? (double) totalDuration / callCount : 0;
            System.out.println("Agent ID: " + agentId + ", Total Duration: " + totalDuration + " seconds, Average Duration: " + averageDuration + " seconds, Calls Received: " + callCount);
        }
    }

    public Map<String, Integer> analyzeCallCounts(List<CallLog> callLogs) {
        Map<String, Integer> agentCallCounts = new HashMap<>();
        for (CallLog log : callLogs) {
            agentCallCounts.merge(log.getAgentId(), 1, Integer::sum);
        }
        return agentCallCounts;
    }

    public void identifyTrendsAndPatterns(List<CallLog> callLogs) {
        Map<Integer, Long> callVolumeByTimeOfDay = analyzeCallVolumeByTimeOfDay(callLogs);
        Map<String, Double> callDurationByOutcome = analyzeCallDurationByOutcome(callLogs);
        Map<String, Double> agentPerformance = analyzeAgentPerformance(callLogs);
        Map<String, Double> customerSatisfactionByAgent = analyzeCustomerSatisfactionByAgent(callLogs);
        Map<String, Integer> agentCallCounts = analyzeCallCounts(callLogs);
        Map<String, Integer> agentCallDurations = analyzeCallDurations(callLogs);


        printCallVolumeByTimeOfDay(callVolumeByTimeOfDay);
        printCallDurationByOutcome(callDurationByOutcome);
        printAgentPerformance(agentPerformance);
        printCustomerSatisfactionByAgent(customerSatisfactionByAgent);
        generateCallDurationReport(agentCallDurations, agentCallCounts);

    }

    private void printCallVolumeByTimeOfDay(Map<Integer, Long> callVolumeByTimeOfDay) {
        System.out.println("Call Volume by Time of Day:");
        callVolumeByTimeOfDay.forEach((hour, count) -> System.out.println("Hour: " + hour + ", Count: " + count));
    }

    private void printCallDurationByOutcome(Map<String, Double> callDurationByOutcome) {
        System.out.println("\nCall Duration by Outcome:");
        callDurationByOutcome.forEach((outcome, avgDuration) -> System.out.println("Outcome: " + outcome + ", Average Duration: " + avgDuration + " seconds"));
    }

    private void printAgentPerformance(Map<String, Double> agentPerformance) {
        System.out.println("\nAgent Performance (Average Call Duration):");
        agentPerformance.forEach((agentId, avgDuration) -> System.out.println("Agent ID: " + agentId + ", Average Duration: " + avgDuration + " seconds"));
    }


    private void printCustomerSatisfactionByAgent(Map<String, Double> customerSatisfactionByAgent) {
        System.out.println("\nCustomer Satisfaction by Agent:");
        customerSatisfactionByAgent.forEach((agentId, avgSatisfaction) -> System.out.println("Agent ID: " + agentId + ", Average Satisfaction: " + avgSatisfaction));
    }


}
