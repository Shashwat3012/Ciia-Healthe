package com.example.healthe.entity;

import javax.persistence.*;

@Entity
@Table(name = "medication")
public class Medication {
    @Column(name = "disease")
    private String disease;

    @Column(name = "medicine")
    private String medicine;

    @Column(name = "doctor")
    private String doctor;

    @Column(name = "start_Date")
    private String start_Date;

    @Column(name = "end_Date")
    private String end_Date;

    @Column(name = "reports")
    private String reports;

    @Column(name = "patientId")
    private String patientId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Medication(){}

    public Medication(String disease, String medicine, String doctor, String start_Date, String end_Date, String reports, String patientId) {
        this.disease = disease;
        this.medicine = medicine;
        this.doctor = doctor;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.reports = reports;
        this.patientId = patientId;
        this.id = id;
    }

    public String getDisease() { return disease; }

    public String getMedicine() { return medicine; }

    public String getDoctor() { return doctor; }

    public String getStart_Date() { return start_Date; }

    public String getEnd_Date() { return end_Date; }

    public String getReports() { return reports; }

    public long getId() { return id; }

    public String getPatientId() { return patientId; }

    @Override
    public String toString() {
        return "Medication{" +
                "disease='" + disease + '\'' +
                ", medicine='" + medicine + '\'' +
                ", doctor='" + doctor + '\'' +
                ", start_Date='" + start_Date + '\'' +
                ", end_Date='" + end_Date + '\'' +
                ", reports='" + reports + '\'' +
                ", patientId='" + patientId + '\'' +
                ", id=" + id +
                '}';
    }
}
