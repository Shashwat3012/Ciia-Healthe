package com.example.healthe.repository;

import com.example.healthe.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query("Select u from Medication u WHERE u.patientId =:patientId")
    List<Medication> findByPatientId(@Param("patientId") String patientId);

    @Transactional
    @Modifying
    @Query("update Medication u set u.disease =:disease,u.medicine =:medicine," +
            "u.doctor =:doctor, u.start_Date =:start_Date, u.end_Date =:end_Date, " +
            "u.reports =:reports where u.patientId =:patientId")
    void updateMedInfo(@Param(value = "disease") String disease,
                       @Param(value = "medicine") String medicine,
                       @Param(value = "doctor") String doctor,
                       @Param(value = "start_Date") String start_Date,
                       @Param(value = "end_Date") String end_Date,
                       @Param(value = "reports") String reports,
                       @Param(value = "patientId") String patientId);

}