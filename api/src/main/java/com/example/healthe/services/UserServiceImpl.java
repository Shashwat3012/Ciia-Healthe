package com.example.healthe.services;

import com.example.healthe.data.request.*;
import com.example.healthe.entity.DoctorInfo;
import com.example.healthe.entity.PatientInfo;
import com.example.healthe.repository.DoctorRepository;
import com.example.healthe.repository.DoctorRequestRepository;
import com.example.healthe.repository.PatientInfoRepository;
import com.example.healthe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements User{
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PatientInfoRepository patientRepo;
    @Autowired
    private DoctorRequestRepository doctorRepo;
    @Autowired
    private DoctorRepository doctorUserRepo;

    @Override
    public String registerUser(RegisterUserRequest userRequest) {
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        System.out.println(userRequest.getRole());
        if(Objects.equals(userRequest.getRole(), "Patient")) {
            userRepo.save(new com.example.healthe.entity.User(userRequest.getFirstName(),
                    userRequest.getLastName(), userRequest.getUserName(),
                    userRequest.getPassword(), userRequest.getRole(), uuid));
        }else{
            doctorUserRepo.save(new DoctorInfo(userRequest.getFirstName(),
                    userRequest.getLastName(), userRequest.getUserName(),
                    userRequest.getPassword(), userRequest.getRole(), uuid,userRequest.getLicense(),
                    userRequest.getHospital(),userRequest.getStatus()));
        }
        return uuid;
    }

    @Override
    public String loginUser(LoginUserRequest userRequest) {
        if(Objects.equals(userRequest.getRole(), "Patient")) {
            com.example.healthe.entity.User user =
                    userRepo.findByUsernameAndPassword(userRequest.getUserName(), userRequest.getPassword());
            if (user == null) {
                return "User Not Found!";
            } else {
                return user.getUuid();
            }
        }
        else if(Objects.equals(userRequest.getRole(), "Nominee")){
            PatientInfo pInfo = patientRepo.findByPatientsByNominee(userRequest.getPatientId(),
                                                                userRequest.getNomineeName());
            if (pInfo == null) {
                return "User Not Found!";
            } else {
                return "Successful Login";
            }
        } else{
            DoctorInfo pInfo = doctorUserRepo.findByUsernameAndPassword(userRequest.getUserName(),
                    userRequest.getPassword());
            if (pInfo == null) {
                return "User Not Found!";
            } else {
                if(Objects.equals(pInfo.getStatus(), "Approved")) {
                    return "Successful Login";
                }
                else return "Status Pending";
            }
        }
    }

    @Override
    public String saveUser(PatientInfoRequest patientInfo) {
        patientRepo.save(new PatientInfo(patientInfo.getPatientName(),patientInfo.getPatientId(),patientInfo.getDOB(),
                patientInfo.getHeight(),patientInfo.getWeight(),
                patientInfo.getDisease(),patientInfo.getBloodGroup(),patientInfo.getNominee1Name(),
                patientInfo.getNominee1Contact(),patientInfo.getNominee2Name(),patientInfo.getNominee2Contact()));
        return "Submitted Successfully";
    }

    @Override
    public String requestData(DoctorRequest doctorRequest){
        String requestId = UUID.randomUUID().toString().substring(0, 6);
        doctorRepo.save(new com.example.healthe.entity.DoctorRequest(requestId,doctorRequest.getpatientId(),
                doctorRequest.getReason(),doctorRequest.getDoctorId(),doctorRequest.getDate(),
                doctorRequest.getStatus()));
        return "Status Pending!";
    }

    @Override
    public PatientInfoRequest getPatientInfo(String patientId) throws InterruptedException {
        PatientInfo patientList =  patientRepo.findByPatientId(patientId);
        PatientInfoRequest pRequest = new PatientInfoRequest(
                patientList.getPatientName(),
                patientList.getPatientId(),
                patientList.getDOB(),
                patientList.getHeight(),
                patientList.getWeight(),
                patientList.getDisease(),
                patientList.getBloodGroup(),
                patientList.getNominee1Name(),
                patientList.getNominee1Contact(),
                patientList.getNominee2Name(),
                patientList.getNominee2Contact()
        );
        return pRequest;
    }

    @Override
    public List<com.example.healthe.entity.DoctorRequest> getRequestsForPatient(String patientId) {
        List<com.example.healthe.entity.DoctorRequest> patientRequests =  doctorRepo.findByPatientId(patientId);
        return patientRequests;
    }

    public List<com.example.healthe.entity.DoctorRequest> getRequestsByDoctor(String doctorId) {
        List<com.example.healthe.entity.DoctorRequest> doctorRequests =  doctorRepo.findByDoctorId(doctorId);
        return doctorRequests;
    }

    @Override
    public String editUser(PatientInfoRequest patientInfo) {
        patientRepo.updatePatientInfo(patientInfo.getPatientId(),patientInfo.getHeight(),
                patientInfo.getWeight(),
                patientInfo.getDisease(),patientInfo.getBloodGroup(),
                patientInfo.getNominee1Name(),patientInfo.getNominee1Contact(),patientInfo.getNominee2Name(),
                patientInfo.getNominee2Contact());
        return "Submitted Successfully";
    }

    @Override
    public ArrayList<PatientInfoRequest> getPatientInfoByDisease(String disease) {
        List<PatientInfo> patientList =  patientRepo.findByPatientsByDisease(disease);
        ArrayList<PatientInfoRequest> pList = new ArrayList<>();
        for(PatientInfo patientObject: patientList) {
            PatientInfoRequest pRequest = new PatientInfoRequest(
                    patientObject.getPatientName(),
                    patientObject.getPatientId(),
                    patientObject.getDOB(),
                    patientObject.getHeight(),
                    patientObject.getWeight(),
                    patientObject.getDisease(),
                    patientObject.getBloodGroup(),
                    patientObject.getNominee1Name(),
                    patientObject.getNominee1Contact(),
                    patientObject.getNominee2Name(),
                    patientObject.getNominee2Contact()
            );
            pList.add(pRequest);
        }
        return  pList;
    }

    @Override
    public String updateDoctorUser(String doctorId) {
        doctorUserRepo.updateDoctorStatus("Approved",doctorId);
        return "Approved";
    }

    @Override
    public List<DoctorInfo> fetchAllDoctors() {
        return doctorUserRepo.findAll();
    }

    @Override
    public String updateRequestStatus(UpdateDoctorRequest docRequest) {
        doctorRepo.updateStatus(docRequest.getStatus(),docRequest.getRequestId());
        return docRequest.getStatus();
    }


}
