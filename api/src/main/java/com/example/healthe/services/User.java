package com.example.healthe.services;

import com.example.healthe.data.request.*;
import com.example.healthe.data.response.FileResponse;
import com.example.healthe.entity.DoctorInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface User {
    String registerUser(RegisterUserRequest userRequest);

    String loginUser(LoginUserRequest userRequest);

    String saveUser(PatientInfoRequest patientInfo);

    String saveMedData(MedicationRequest medicationRequest);

    String saveAllergData(AllergiesRequest allergiesRequest);

    String saveInjuryData(InjuryHistoryRequest injuryRequest);

    String saveFileData(FileRequest fileRequest);

    String requestData(DoctorRequest doctorRequest);

    PatientInfoRequest getPatientInfo(String patientId) throws InterruptedException;

    ArrayList<MedicationRequest> getMedicationData(String patientId) throws InterruptedException;

    ArrayList<AllergiesRequest> getAllergiesData(String patientId) throws InterruptedException;

    ArrayList<InjuryHistoryRequest> getInjuryData(String patientId) throws InterruptedException;

    ArrayList<FileResponse> getFileData(String patientId) throws InterruptedException;

    List<com.example.healthe.entity.DoctorRequest> getRequestsForPatient(String patientId);

    String updateRequestStatus(UpdateDoctorRequest docRequest);

    List<com.example.healthe.entity.DoctorRequest> getRequestsByDoctor(String doctorId);

    String editUser(PatientInfoRequest patientInfo);

    String editMedData(MedicationRequest medicationRequest);

    String editAllergData(AllergiesRequest allergiesRequest);

    String editInjuryData(InjuryHistoryRequest injuryHistoryRequest);

    String editFileData(FileRequest fileRequest);

    ArrayList<PatientInfoRequest> getPatientInfoByDisease(String disease);

    String updateDoctorUser(String doctorId);

    List<DoctorInfo> fetchAllDoctors();

    String uploadFile(FileRequest file);
}
