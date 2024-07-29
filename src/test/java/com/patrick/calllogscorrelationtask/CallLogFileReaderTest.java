package com.patrick.calllogscorrelationtask;

import com.patrick.calllogscorrelationtask.models.CallLog;
import com.patrick.calllogscorrelationtask.utils.CallLogFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CallLogFileReaderTest {

    @Test
    void testReadCallLogs() throws IOException {
        String tempFile = Files.createTempFile("call_logs", ".csv").toString();
        String csvContent = "callStartTime,callEndTime,agentId,callerId,callDuration,callOutcome,customerSatisfaction\n" +
                "2024-07-02 10:21:05,2024-07-02 10:25:30,agent1,caller1,265,success,5\n" +
                "2024-07-02 11:00:00,2024-07-02 11:10:00,agent2,caller2,600,failed,3";

        Files.write(Path.of(tempFile), csvContent.getBytes());

        CallLogFileReader reader = new CallLogFileReader();
        List<CallLog> callLogs = reader.readCallLogs(tempFile);

        assertEquals(2, callLogs.size());

        CallLog log1 = callLogs.get(0);
        assertEquals(LocalDateTime.of(2024, 7, 2, 10, 21, 5), log1.getCallStartTime());
        assertEquals(LocalDateTime.of(2024, 7, 2, 10, 25, 30), log1.getCallEndTime());
        assertEquals("agent1", log1.getAgentId());
        assertEquals("caller1", log1.getCallerId());
        assertEquals(265, log1.getCallDuration());
        assertEquals("success", log1.getCallOutcome());
        assertEquals(5, log1.getCustomerSatisfaction());

        CallLog log2 = callLogs.get(1);
        assertEquals(LocalDateTime.of(2024, 7, 2, 11, 0, 0), log2.getCallStartTime());
        assertEquals(LocalDateTime.of(2024, 7, 2, 11, 10, 0), log2.getCallEndTime());
        assertEquals("agent2", log2.getAgentId());
        assertEquals("caller2", log2.getCallerId());
        assertEquals(600, log2.getCallDuration());
        assertEquals("failed", log2.getCallOutcome());
        assertEquals(3, log2.getCustomerSatisfaction());

        Files.delete(Path.of(tempFile));
    }

    @Test
    void testReadCallLogsFileNotFound() {
        CallLogFileReader reader = new CallLogFileReader();
        assertThrows(IOException.class, () -> reader.readCallLogs("non_existent_file.csv"));
    }
}
