package com.example.healthe.entity;

import javax.persistence.*;

@Entity
@Table(name = "allergies")
public class Allergies {
    @Column(name = "allergic_To")
    private String allergic_To;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "medicine")
    private String medicine;

    @Column(name = "reports")
    private String reports;

    @Column(name = "patientId")
    private String patientId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Allergies(){ }

    public Allergies(String allergic_To, String symptoms, String medicine, String reports, String patientId) {
        this.allergic_To = allergic_To;
        this.symptoms = symptoms;
        this.medicine = medicine;
        this.reports = reports;
        this.patientId = patientId;
        this.id = id;
    }

    public String getAllergic_To() { return allergic_To; }

    public String getSymptoms() { return symptoms; }

    public String getMedicine() { return medicine; }

    public String getReports() { return reports; }

    public long getId() { return id; }

    public String getPatientId() { return patientId; }

    @Override
    public String toString() {
        return "Allergies{" +
                "allergic_To='" + allergic_To + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", medicine='" + medicine + '\'' +
                ", reports='" + reports + '\'' +
                ", patientId='" + patientId + '\'' +
                ", id=" + id +
                '}';
    }
}
