package com.patrick.calllogscorrelationtask;
import com.patrick.calllogscorrelationtask.models.CallLog;
import com.patrick.calllogscorrelationtask.service.CallLogAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallLogAnalyzerTest {

    private List<CallLog> callLogs;

    @BeforeEach
    void setUp() {
        callLogs = Arrays.asList(
                createCallLog("2024-07-02T10:21:05", "2024-07-02T10:25:30", "agent1", "caller1", 265, "success", 5),
                createCallLog("2024-07-02T11:00:00", "2024-07-02T11:10:00", "agent2", "caller2", 600, "failed", 3),
                createCallLog("2024-07-02T12:00:00", "2024-07-02T12:05:00", "agent1", "caller3", 300, "success", 4)
        );
    }

    private CallLog createCallLog(String start, String end, String agentId, String callerId, int duration, String outcome, int satisfaction) {
        CallLog log = new CallLog();
        log.setCallStartTime(LocalDateTime.parse(start));
        log.setCallEndTime(LocalDateTime.parse(end));
        log.setAgentId(agentId);
        log.setCallerId(callerId);
        log.setCallDuration(duration);
        log.setCallOutcome(outcome);
        log.setCustomerSatisfaction(satisfaction);
        return log;
    }

    @Test
    void testAnalyzeCallCounts() {
        CallLogAnalyzer analyzer = new CallLogAnalyzer();
        Map<String, Integer> result = analyzer.analyzeCallCounts(callLogs);
        assertEquals(2, result.size());
        assertEquals(2, result.get("agent1").intValue());
        assertEquals(1, result.get("agent2").intValue());
    }

    @Test
    void testGenerateCallDurationReport() {
        CallLogAnalyzer analyzer = new CallLogAnalyzer();
        Map<String, Integer> callCounts = analyzer.analyzeCallCounts(callLogs);
        Map<String, Integer> callDurations = analyzer.analyzeCallDurations(callLogs);
        analyzer.generateCallDurationReport(callDurations, callCounts);

        // Manual verification needed for console output or redirect output to verify
    }

    @Test
    void testAnalyzeCallDurations() {
        CallLogAnalyzer analyzer = new CallLogAnalyzer();
        Map<String, Integer> result = analyzer.analyzeCallDurations(callLogs);
        assertEquals(2, result.size());
        assertEquals(565, result.get("agent1").intValue());
        assertEquals(600, result.get("agent2").intValue());
    }

    @Test
    void testAnalyzeCallOutcomes() {
        CallLogAnalyzer analyzer = new CallLogAnalyzer();
        Map<String, Integer> result = analyzer.analyzeCallOutcomes(callLogs);
        assertEquals(2, result.size());
        assertEquals(2, result.get("success").intValue());
        assertEquals(1, result.get("failed").intValue());
    }

    @Test
    void testAnalyzeCallVolumeByTimeOfDay() {
        CallLogAnalyzer analyzer = new CallLogAnalyzer();
        Map<Integer, Long> result = analyzer.analyzeCallVolumeByTimeOfDay(callLogs);
        assertEquals(3, result.size());
        assertEquals(1L, result.get(10).longValue());
        assertEquals(1L, result.get(11).longValue());
        assertEquals(1L, result.get(12).longValue());
    }

    @Test
    void testAnalyzeCallDurationByOutcome() {
        CallLogAnalyzer analyzer = new CallLogAnalyzer();
        Map<String, Double> result = analyzer.analyzeCallDurationByOutcome(callLogs);
        assertEquals(2, result.size());
        assertEquals(282.5, result.get("success"), 0.01);
        assertEquals(600.0, result.get("failed"), 0.01);
    }

    @Test
    void testAnalyzeAgentPerformance() {
        CallLogAnalyzer analyzer = new CallLogAnalyzer();
        Map<String, Double> result = analyzer.analyzeAgentPerformance(callLogs);
        assertEquals(2, result.size());
        assertEquals(282.5, result.get("agent1"), 0.01);
        assertEquals(600.0, result.get("agent2"), 0.01);
    }

    @Test
    void testAnalyzeCustomerSatisfactionByAgent() {
        CallLogAnalyzer analyzer = new CallLogAnalyzer();
        Map<String, Double> result = analyzer.analyzeCustomerSatisfactionByAgent(callLogs);
        assertEquals(2, result.size());
        assertEquals(4.5, result.get("agent1"), 0.01);
        assertEquals(3.0, result.get("agent2"), 0.01);
    }

}
