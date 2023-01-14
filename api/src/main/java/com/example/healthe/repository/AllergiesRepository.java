package com.example.healthe.repository;

import com.example.healthe.entity.Allergies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AllergiesRepository extends JpaRepository<Allergies, Long>{
    @Query("Select u from Allergies u WHERE u.patientId=:patientId")
    List<Allergies> findByPatientId(@Param("patientId") String patientId);

    @Transactional
    @Modifying
    @Query("update Allergies u set u.allergic_To =:allergic_To, u.reports =:reports," +
            "u.symptoms =:symptoms, u.medicine =:medicine where u.patientId =:patientId")

    void updateAllergiesInfo(@Param(value = "patientId") String patientId,
                             @Param(value = "allergic_To") String allergic_To,
                             @Param(value = "reports") String reports,
                             @Param(value = "symptoms") String symptoms,
                             @Param(value = "medicine") String medicine);

}
