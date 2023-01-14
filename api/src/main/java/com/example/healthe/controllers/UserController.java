package com.example.healthe.controllers;

import com.example.healthe.data.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class UserController {
    @Autowired
    private com.example.healthe.services.User userService;

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginUserRequest userRequest) {
        return userService.loginUser(userRequest);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterUserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    @PostMapping("/updateDoctorUser")
    public String updateDoctorUser(@RequestBody String doctorId) {
        return userService.updateDoctorUser(doctorId);
    }

    @PostMapping("/savePatientData")
    public String saveData(@RequestBody PatientInfoRequest patientInfo) {
        return userService.saveUser(patientInfo);
    }

    @PostMapping("/editPatientData")
    public String editData(@RequestBody PatientInfoRequest patientInfo) {
        return userService.editUser(patientInfo);
    }

    // Start Postman Data entering from here

    @PostMapping("/saveMedicationData")
    public String saveData(@RequestBody MedicationRequest medicationRequest) {
        return userService.saveMedData(medicationRequest);
    }

    @PostMapping("/editMedicationData")
    public String editData(@RequestBody MedicationRequest medicationRequest) {
        return userService.editMedData(medicationRequest);
    }

    @PostMapping("/saveAllergiesData")
    public String saveData(@RequestBody AllergiesRequest allergiesRequest) {
        return userService.saveAllergData(allergiesRequest);
    }

    @PostMapping("/editAllergiesData")
    public String editData(@RequestBody AllergiesRequest allergiesRequest) {
        return userService.editAllergData(allergiesRequest);
    }

    @PostMapping("/saveInjuryData")
    public String saveData(@RequestBody InjuryHistoryRequest injuryHistoryRequest) {
        return userService.saveInjuryData(injuryHistoryRequest);
    }

    @PostMapping("/editInjuryData")
    public String editData(@RequestBody InjuryHistoryRequest injuryHistoryRequest) {
        return userService.editInjuryData(injuryHistoryRequest);
    }

    @PostMapping("/saveFileData")
    public String saveData(@RequestBody FileRequest fileRequest) {
        return userService.saveFileData(fileRequest);
    }

    @PostMapping("/editFileData")
    public String editData(@RequestBody FileRequest fileRequest) {
        return userService.editFileData(fileRequest);
    }

    // End the Postman Data entering here

    @PostMapping("/requestPatientInfo")
    public String requestPatientInfo(@RequestBody DoctorRequest doctorRequest){
        return userService.requestData(doctorRequest);
    }

    @GetMapping("/getRequests")
    public List<com.example.healthe.entity.DoctorRequest> getRequests(@RequestParam String patientId) throws InterruptedException {
        try{
            return userService.getRequestsForPatient(patientId);
        } catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/getRequestsByDoctor")
    public List<com.example.healthe.entity.DoctorRequest> getRequestsByDoctor(@RequestParam String doctorId) throws InterruptedException {
        try{
            return userService.getRequestsByDoctor(doctorId);
        } catch(Exception e){
            throw e;
        }

    }

    @GetMapping("/patientInfo")
    public PatientInfoRequest getPatientInfo(@RequestParam String patientId) throws InterruptedException {
        try{
            return userService.getPatientInfo(patientId);
        } catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/medicationData")
    public ArrayList<MedicationRequest> getMedicationData(@RequestParam String patientId) throws InterruptedException {
        try{
            return userService.getMedicationData(patientId);
        } catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/allergiesData")
    public ArrayList<AllergiesRequest> getAllergiesData(String patientId) throws InterruptedException  {
        try{
            return userService.getAllergiesData(patientId);
        } catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/injuryData")
    public ArrayList<InjuryHistoryRequest> getInjuryData(@RequestParam String patientId) throws InterruptedException {
        try{
            return userService.getInjuryData(patientId);
        } catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/fileData")
    public ArrayList<FileRequest> getFileData(String patientId) throws InterruptedException {
        try{
            return userService.getFileData(patientId);
        } catch(Exception e){
            throw e;
        }
    }

    @PostMapping("/file")
    public String uploadFile(FileRequest file){
        try{
            return userService.uploadFile(file);
        }catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/patientInfoByDisease")
    public ArrayList<PatientInfoRequest> getPatientInfoByDisease(@RequestParam String disease) throws InterruptedException {
        try{
            return userService.getPatientInfoByDisease(disease);
        } catch(Exception e){
            throw e;
        }
    }

    @PostMapping("/updateRequestStatus")
    public String updateRequestStatus(@RequestBody UpdateDoctorRequest docRequest){
        return userService.updateRequestStatus(docRequest);
    }

    @GetMapping("/fetchAllDoctors")
    public List<com.example.healthe.entity.DoctorInfo> fetchAllDoctors() throws InterruptedException {
        try{
            return userService.fetchAllDoctors();
        } catch(Exception e){
            throw e;
        }
    }


}
