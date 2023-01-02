package com.example.healthe.data.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class InjuryHistoryRequest {
    private String injury;
    private String date;
    private String reports;
    private String patientId;

    public InjuryHistoryRequest(String injury, String date, String reports, String patientId) {
        this.injury = injury;
        this.date = date;
        this.reports = reports;
        this.patientId = patientId;
    }

    public String getInjury() { return injury; }

    public String getDate() { return date; }

    public String getReports() { return reports; }

    public String getPatientId() { return patientId; }
}
