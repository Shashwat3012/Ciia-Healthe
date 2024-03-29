package com.example.healthe.repository;

import com.example.healthe.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {
    //ideally only one object to be returned
//    PatientInfo findByuuid(@Param("uuid") String uuid);
    @Query("Select u from PatientInfo u WHERE u.patientId=:patientId")
    PatientInfo findByPatientId(@Param("patientId") String patientId);

    @Transactional
    @Modifying
    @Query("update PatientInfo u set u.height =:height, u.weight =:weight, " +
            "u.disease =:disease, u.bloodGroup =:bloodGroup, " +
            "u.nominee1Name =:nominee1Name, " +
            "u.nominee1Contact =:nominee1Contact, u.nominee2Name =:nominee2Name, " +
            "u.nominee2Contact =:nominee2Contact where u.patientId =:patientId")
    void updatePatientInfo(@Param(value = "patientId") String patientId,
                           @Param(value = "height") Float height,
                           @Param(value = "weight") Float weight,
                           @Param(value = "disease") String disease,
                           @Param(value = "bloodGroup") String bloodGroup,
                           @Param(value = "nominee1Name") String nominee1Name, String nominee1Contact, String nominee2Name, String nominee2Contact);


    @Query("Select u from PatientInfo u WHERE u.disease=:disease")
    List<PatientInfo> findByPatientsByDisease(@Param("disease")String disease);

    @Query("Select u from PatientInfo u WHERE u.patientId=:patientId AND (u.nominee1Name =:nomineeName OR u.nominee2Name=:nomineeName)")
    PatientInfo findByPatientsByNominee(@Param("patientId")String patientId, @Param("nomineeName")String nomineeName);

}
