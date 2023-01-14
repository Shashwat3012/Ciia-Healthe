package com.example.healthe.entity;

import javax.persistence.*;

@Entity
@Table(name = "File")
public class File {
    @Column(name = "extension")
    private String extension;

    @Column(name = "upload_Date")
    private String upload_Date;

    @Column(name = "file_Name")
    private String file_Name;

    @Column(name = "patientId")
    private String patientId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fileId;

    public File(){ }

    public File(String extension, String upload_Date, String file_Name, String patientId) {
        this.extension = extension;
        this.upload_Date = upload_Date;
        this.file_Name = file_Name;
        this.patientId = patientId;
        this.fileId = fileId;
    }

    public String getExtension() { return extension; }

    public String getUpload_Date() { return upload_Date; }

    public String getFile_Name() { return file_Name; }

    public long getFileId() { return fileId; }

    public String getpatientId() { return patientId; }

    @Override
    public String toString() {
        return "File{" +
                "extension='" + extension + '\'' +
                ", upload_Date='" + upload_Date + '\'' +
                ", file_Name='" + file_Name + '\'' +
                ", patientId='" + patientId + '\'' +
                ", fileId=" + fileId +
                '}';
    }
}
