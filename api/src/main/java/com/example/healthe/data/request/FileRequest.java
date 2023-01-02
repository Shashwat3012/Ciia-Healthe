package com.example.healthe.data.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FileRequest {
    private String extension;
    private String upload_Date;
    private String file_Name;
    private String patientId;

    public FileRequest(String extension, String upload_Date, String file_Name, String patientId) {
        this.extension = extension;
        this.upload_Date = upload_Date;
        this.file_Name = file_Name;
        this.patientId = patientId;
    }

    public String getExtension() { return extension; }

    public String getUpload_Date() { return upload_Date; }

    public String getFile_Name() { return file_Name; }

    public String getPatientId() { return patientId; }
}
