package com.example.healthe.entity;

import javax.persistence.*;

@Entity
@Table(name = "injury_history")
public class InjuryHistory {
    @Column(name = "injury")
    private String injury;

    @Column(name = "date")
    private String date;

    @Column(name = "reports")
    private String reports;

    @Column(name = "patientId")
    private String patientId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public InjuryHistory(){ }

    public InjuryHistory(String injury, String date, String reports, String patientId) {
        this.injury = injury;
        this.date = date;
        this.reports = reports;
        this.patientId = patientId;
        this.id = id;
    }

    public String getInjury() { return injury; }

    public String getDate() { return date; }

    public String getReports() { return reports; }

    public long getId() { return id; }

    public String getPatientId() { return patientId; }

    @Override
    public String toString() {
        return "InjuryHistory{" +
                "injury='" + injury + '\'' +
                ", date='" + date + '\'' +
                ", reports='" + reports + '\'' +
                ", patientId='" + patientId + '\'' +
                ", id=" + id +
                '}';
    }
}
