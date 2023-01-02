package com.example.healthe.data.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MedicationRequest {
    private String disease;
    private String medicine;
    private String doctor;
    private String start_Date;
    private String end_Date;
    private String reports;
    private String patientId;

    public MedicationRequest(String disease, String medicine, String doctor, String start_Date,
                             String end_Date, String reports, String patientId) {
        this.disease = disease;
        this.medicine = medicine;
        this.doctor = doctor;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.reports = reports;
        this.patientId = patientId;
    }

    public String getDisease() { return disease; }

    public String getMedicine() { return medicine; }

    public String getDoctor() { return doctor; }

    public String getStart_Date() { return start_Date; }

    public String getEnd_Date() { return end_Date; }

    public String getReports() { return reports; }

    public String getPatientId() { return patientId; }
}

