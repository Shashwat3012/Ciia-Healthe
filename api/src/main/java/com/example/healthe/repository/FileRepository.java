package com.example.healthe.repository;

import com.example.healthe.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FileRepository extends JpaRepository<File, Long>{
    @Query("Select u from File u WHERE u.patientId=:patientId")
    File findByPatientId(@Param("patientId") String patientId);

    @Transactional
    @Modifying
    @Query("update File u set u.extension =:extension, u.upload_Date =:upload_Date," +
            "u.file_Name =:file_Name where u.patientId =:patientId")

    void updateFileInfo(@Param(value = "patientId") String patientId,
                             @Param(value = "extension") String extension,
                             @Param(value = "upload_Date") String upload_Date,
                             @Param(value = "file_Name") String file_Name);

}
