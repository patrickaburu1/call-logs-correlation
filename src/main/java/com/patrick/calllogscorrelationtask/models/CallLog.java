package com.patrick.calllogscorrelationtask.models;

import java.time.LocalDateTime;

public class CallLog {

    private LocalDateTime callStartTime;
    private LocalDateTime callEndTime;
    private String agentId;
    private String callerId;
    private Integer callDuration; // in seconds
    private String callOutcome;
    private int customerSatisfaction; // e.g., rating out of 5

    public LocalDateTime getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(LocalDateTime callStartTime) {
        this.callStartTime = callStartTime;
    }

    public LocalDateTime getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(LocalDateTime callEndTime) {
        this.callEndTime = callEndTime;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    public String getCallOutcome() {
        return callOutcome;
    }

    public void setCallOutcome(String callOutcome) {
        this.callOutcome = callOutcome;
    }

    public int getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    public void setCustomerSatisfaction(int customerSatisfaction) {
        this.customerSatisfaction = customerSatisfaction;
    }
}
