package com.example.healthe.data.response;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileResponse {
    private String extension;
    private String uploadDate;
    private String fileName;
    private String patientId;
    private File file;

    public FileResponse(String extension, String uploadDate, String fileName, String patientId, File file) {
        this.extension = extension;
        this.uploadDate = uploadDate;
        this.fileName = fileName;
        this.patientId = patientId;
        this.file = file;
    }

    public FileResponse() {
    }

    public String getExtension() {
        return extension;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPatientId() {
        return patientId;
    }

    public File getFile() {
        return file;
    }
}
