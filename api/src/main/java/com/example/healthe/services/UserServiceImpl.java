package com.example.healthe.services;

import com.example.healthe.data.request.*;
import com.example.healthe.data.request.DoctorRequest;
import com.example.healthe.entity.*;
import com.example.healthe.repository.*;
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
    @Autowired
    private MedicationRepository medicationRepo;
    @Autowired
    private AllergiesRepository allergiesRepo;
    @Autowired
    private InjuryHistoryRepository injuryHistoryRepo;
    @Autowired
    private FileRepository fileRepo;

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
    public String editUser(PatientInfoRequest patientInfo) {
        patientRepo.updatePatientInfo(patientInfo.getPatientId(),patientInfo.getHeight(),
                patientInfo.getWeight(),
                patientInfo.getDisease(),patientInfo.getBloodGroup(),
                patientInfo.getNominee1Name(),patientInfo.getNominee1Contact(),patientInfo.getNominee2Name(),
                patientInfo.getNominee2Contact());
        return "Submitted Successfully";
    }

    @Override
    public String saveMedData(MedicationRequest medicationRequest) {
        medicationRepo.save(new Medication(medicationRequest.getDisease(),medicationRequest.getMedicine(),
                medicationRequest.getDoctor(),medicationRequest.getStart_Date(),medicationRequest.getEnd_Date(),
                medicationRequest.getReports(),medicationRequest.getPatientId()));
        return "Submitted MedData";
    }

    @Override
    public String editMedData(MedicationRequest medicationRequest) {
        medicationRepo.updateMedInfo(medicationRequest.getDisease(),medicationRequest.getMedicine(),
                medicationRequest.getDoctor(),medicationRequest.getStart_Date(),medicationRequest.getEnd_Date(),
                medicationRequest.getReports(),medicationRequest.getPatientId());
        return "Edited MedData";
    }

    @Override
    public String saveAllergData(AllergiesRequest allergiesRequest) {
        allergiesRepo.save(new Allergies(allergiesRequest.getAllergic_To(),allergiesRequest.getSymptoms(),
        allergiesRequest.getMedicine(),allergiesRequest.getReports(),allergiesRequest.getPatientId()));
        return "Submitted AllergiesData";
    }

    @Override
    public String editAllergData(AllergiesRequest allergiesRequest) {
        allergiesRepo.updateAllergiesInfo(allergiesRequest.getAllergic_To(),allergiesRequest.getSymptoms(),
                allergiesRequest.getMedicine(),allergiesRequest.getReports(),allergiesRequest.getPatientId());
        return "Edited AllergiesData";
    }

    @Override
    public String saveInjuryData(InjuryHistoryRequest injuryHistoryRequest) {
        injuryHistoryRepo.save(new InjuryHistory(injuryHistoryRequest.getInjury(),injuryHistoryRequest.getDate(),
                injuryHistoryRequest.getReports(), injuryHistoryRequest.getPatientId()));
        return "Submitted InjuryData";
    }

    @Override
    public String editInjuryData(InjuryHistoryRequest injuryHistoryRequest) {
        injuryHistoryRepo.updateInjuryHistoryInfo(injuryHistoryRequest.getInjury(),injuryHistoryRequest.getDate(),
                injuryHistoryRequest.getReports(), injuryHistoryRequest.getPatientId());
        return "Edited InjuryData";
    }

    @Override
    public String saveFileData(FileRequest fileRequest) {
        fileRepo.save(new File(fileRequest.getExtension(),fileRequest.getUpload_Date(),
                fileRequest.getFile_Name(), fileRequest.getPatientId()));
        return "Submitted FileData";
    }

    @Override
    public String editFileData(FileRequest fileRequest) {
        fileRepo.updateFileInfo(fileRequest.getExtension(),fileRequest.getUpload_Date(),
                fileRequest.getFile_Name(), fileRequest.getPatientId());
        return "Edited FileData";
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
    public FileRequest getFileData(String patientId) throws InterruptedException {
        File fileList = fileRepo.findByPatientId(patientId);
        FileRequest fRequest = new FileRequest(
                fileList.getExtension(),
                fileList.getUpload_Date(),
                fileList.getFile_Name(),
                fileList.getpatientId()
        );
        return fRequest;
    }

    @Override
    public ArrayList<MedicationRequest> getMedicationData(String patientId) throws InterruptedException {
        List<Medication> medList = medicationRepo.findByPatientId(patientId);
        ArrayList<MedicationRequest> medArray = new ArrayList<>();
        medList.forEach(med -> {
                MedicationRequest mRequest = new MedicationRequest(
                        med.getDisease(),
                        med.getMedicine(),
                        med.getDoctor(),
                        med.getStart_Date(),
                        med.getEnd_Date(),
                        med.getReports(),
                        med.getPatientId()
        );
                medArray.add(mRequest);
        });
        return medArray;
    }

    @Override
    public AllergiesRequest getAllergiesData(String patientId) throws InterruptedException {
        Allergies allergList = allergiesRepo.findByPatientId(patientId);
        AllergiesRequest aRequest = new AllergiesRequest(
                allergList.getAllergic_To(),
                allergList.getSymptoms(),
                allergList.getMedicine(),
                allergList.getReports(),
                allergList.getPatientId()
        );
        return aRequest;
    }

    @Override
    public InjuryHistoryRequest getInjuryData(String patientId) throws InterruptedException{
        InjuryHistory injuryList = injuryHistoryRepo.findByPatientId(patientId);
        InjuryHistoryRequest iRequest = new InjuryHistoryRequest(
                injuryList.getInjury(),
                injuryList.getDate(),
                injuryList.getReports(),
                injuryList.getPatientId()
        );
        return iRequest;
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
