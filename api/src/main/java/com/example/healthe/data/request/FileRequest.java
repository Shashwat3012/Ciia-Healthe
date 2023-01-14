package com.example.healthe.data.request;

import org.springframework.web.multipart.MultipartFile;

public class FileRequest {
    private String extension;
    private String uploadDate;
    private String fileName;
    private String patientId;
    private MultipartFile file;

    public FileRequest(String extension, String uploadDate, String fileName, String patientId, MultipartFile file) {
        this.extension = extension;
        this.uploadDate = uploadDate;
        this.fileName = fileName;
        this.patientId = patientId;
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getExtension() { return extension; }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPatientId() { return patientId; }
}
