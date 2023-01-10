package com.example.healthe.repository;

import com.example.healthe.entity.InjuryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface InjuryHistoryRepository extends JpaRepository<InjuryHistory, Long>{
    @Query("Select u from InjuryHistory u WHERE u.patientId=:patientId")
    InjuryHistory findByPatientId(@Param("patientId") String patientId);

    @Transactional
    @Modifying
    @Query("update InjuryHistory u set u.date =:date, u.reports =:reports," +
            "u.injury =:injury where u.patientId =:patientId")

    void updateInjuryHistoryInfo(@Param(value = "patientId") String patientId,
                             @Param(value = "date") String date,
                             @Param(value = "reports") String reports,
                             @Param(value = "injury") String injury);

}
