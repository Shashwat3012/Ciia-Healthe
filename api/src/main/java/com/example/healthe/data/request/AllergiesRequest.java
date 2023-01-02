package com.example.healthe.data.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AllergiesRequest {
    private String allergic_To;
    private String symptoms;
    private String medicine;
    private String reports;
    private String patientId;

    public AllergiesRequest(String allergic_To, String symptoms, String medicine, String reports, String patientId) {
        this.allergic_To = allergic_To;
        this.symptoms = symptoms;
        this.medicine = medicine;
        this.reports = reports;
        this.patientId = patientId;
    }

    public String getAllergic_To() { return allergic_To; }

    public String getSymptoms() { return symptoms; }

    public String getMedicine() { return medicine; }

    public String getReports() { return reports; }

    public String getPatientId() { return patientId; }
}
