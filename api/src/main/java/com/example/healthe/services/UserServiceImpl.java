package com.example.healthe.services;

import com.example.healthe.data.request.DoctorRequest;
import com.example.healthe.data.request.*;
import com.example.healthe.data.response.FileResponse;
import com.example.healthe.entity.*;
import com.example.healthe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements User {
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
        if (Objects.equals(userRequest.getRole(), "Patient")) {
            userRepo.save(new com.example.healthe.entity.User(userRequest.getFirstName(),
                    userRequest.getLastName(), userRequest.getUserName(),
                    userRequest.getPassword(), userRequest.getRole(), uuid));
        } else {
            doctorUserRepo.save(new DoctorInfo(userRequest.getFirstName(),
                    userRequest.getLastName(), userRequest.getUserName(),
                    userRequest.getPassword(), userRequest.getRole(), uuid, userRequest.getLicense(),
                    userRequest.getHospital(), userRequest.getStatus()));
        }
        return uuid;
    }

    @Override
    public String loginUser(LoginUserRequest userRequest) {
        if (Objects.equals(userRequest.getRole(), "Patient")) {
            com.example.healthe.entity.User user =
                    userRepo.findByUsernameAndPassword(userRequest.getUserName(), userRequest.getPassword());
            if (user == null) {
                return "User Not Found!";
            } else {
                return user.getUuid();
            }
        } else if (Objects.equals(userRequest.getRole(), "Nominee")) {
            PatientInfo pInfo = patientRepo.findByPatientsByNominee(userRequest.getPatientId(),
                    userRequest.getNomineeName());
            if (pInfo == null) {
                return "User Not Found!";
            } else {
                return "Successful Login";
            }
        } else {
            DoctorInfo pInfo = doctorUserRepo.findByUsernameAndPassword(userRequest.getUserName(),
                    userRequest.getPassword());
            if (pInfo == null) {
                return "User Not Found!";
            } else {
                if (Objects.equals(pInfo.getStatus(), "Approved")) {
                    return "Successful Login";
                } else return "Status Pending";
            }
        }
    }

    @Override
    public String saveUser(PatientInfoRequest patientInfo) {
        patientRepo.save(new PatientInfo(patientInfo.getPatientName(), patientInfo.getPatientId(), patientInfo.getDOB(),
                patientInfo.getHeight(), patientInfo.getWeight(),
                patientInfo.getDisease(), patientInfo.getBloodGroup(), patientInfo.getNominee1Name(),
                patientInfo.getNominee1Contact(), patientInfo.getNominee2Name(), patientInfo.getNominee2Contact()));
        return "Submitted Successfully";
    }

    @Override
    public String editUser(PatientInfoRequest patientInfo) {
        patientRepo.updatePatientInfo(patientInfo.getPatientId(), patientInfo.getHeight(),
                patientInfo.getWeight(),
                patientInfo.getDisease(), patientInfo.getBloodGroup(),
                patientInfo.getNominee1Name(), patientInfo.getNominee1Contact(), patientInfo.getNominee2Name(),
                patientInfo.getNominee2Contact());
        return "Submitted Successfully";
    }

    @Override
    public String saveMedData(MedicationRequest medicationRequest) {
        medicationRepo.save(new Medication(medicationRequest.getDisease(), medicationRequest.getMedicine(),
                medicationRequest.getDoctor(), medicationRequest.getStart_Date(), medicationRequest.getEnd_Date(),
                medicationRequest.getReports(), medicationRequest.getPatientId()));
        return "Submitted MedData";
    }

    @Override
    public String editMedData(MedicationRequest medicationRequest) {
        medicationRepo.updateMedInfo(medicationRequest.getDisease(), medicationRequest.getMedicine(),
                medicationRequest.getDoctor(), medicationRequest.getStart_Date(), medicationRequest.getEnd_Date(),
                medicationRequest.getReports(), medicationRequest.getPatientId());
        return "Edited MedData";
    }

    @Override
    public String saveAllergData(AllergiesRequest allergiesRequest) {
        allergiesRepo.save(new Allergies(allergiesRequest.getAllergic_To(), allergiesRequest.getSymptoms(),
                allergiesRequest.getMedicine(), allergiesRequest.getReports(), allergiesRequest.getPatientId()));
        return "Submitted AllergiesData";
    }

    @Override
    public String editAllergData(AllergiesRequest allergiesRequest) {
        allergiesRepo.updateAllergiesInfo(allergiesRequest.getAllergic_To(), allergiesRequest.getSymptoms(),
                allergiesRequest.getMedicine(), allergiesRequest.getReports(), allergiesRequest.getPatientId());
        return "Edited AllergiesData";
    }

    @Override
    public String saveInjuryData(InjuryHistoryRequest injuryHistoryRequest) {
        injuryHistoryRepo.save(new InjuryHistory(injuryHistoryRequest.getInjury(), injuryHistoryRequest.getDate(),
                injuryHistoryRequest.getReports(), injuryHistoryRequest.getPatientId()));
        return "Submitted InjuryData";
    }

    @Override
    public String editInjuryData(InjuryHistoryRequest injuryHistoryRequest) {
        injuryHistoryRepo.updateInjuryHistoryInfo(injuryHistoryRequest.getInjury(), injuryHistoryRequest.getDate(),
                injuryHistoryRequest.getReports(), injuryHistoryRequest.getPatientId());
        return "Edited InjuryData";
    }

    @Override
    public String saveFileData(FileRequest fileRequest) {
        fileRepo.save(new File(fileRequest.getExtension(), fileRequest.getUploadDate(),
                fileRequest.getFileName(), fileRequest.getPatientId()));
        return "Submitted FileData";
    }

    @Override
    public String editFileData(FileRequest fileRequest) {
        fileRepo.updateFileInfo(fileRequest.getExtension(), fileRequest.getUploadDate(),
                fileRequest.getFileName(), fileRequest.getPatientId());
        return "Edited FileData";
    }

    @Override
    public String requestData(DoctorRequest doctorRequest) {
        String requestId = UUID.randomUUID().toString().substring(0, 6);
        doctorRepo.save(new com.example.healthe.entity.DoctorRequest(requestId, doctorRequest.getpatientId(),
                doctorRequest.getReason(), doctorRequest.getDoctorId(), doctorRequest.getDate(),
                doctorRequest.getStatus()));
        return "Status Pending!";
    }

    @Override
    public PatientInfoRequest getPatientInfo(String patientId) throws InterruptedException {
        PatientInfo patientList = patientRepo.findByPatientId(patientId);
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
    public ArrayList<FileResponse> getFileData(String patientId) throws InterruptedException {
//        String path = "classpath:";
        List<File> fileList = fileRepo.findByPatientId(patientId);
        ArrayList<FileResponse> fileArray = new ArrayList<>();
        fileList.forEach(file -> {
           // Resource resource = new ClassPathResource(file.getFile_Name());
            java.io.File fetchedFile = new java.io.File(file.getFile_Name()).getAbsoluteFile();
            FileResponse fRequest = new FileResponse(
                    file.getExtension(),
                    file.getUpload_Date(),
                    file.getFile_Name(),
                    file.getpatientId(),
                    fetchedFile
            );
            fileArray.add(fRequest);

        });
        return fileArray;
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
    public ArrayList<AllergiesRequest> getAllergiesData(String patientId) throws InterruptedException {
        List<Allergies> allergList = allergiesRepo.findByPatientId(patientId);
        ArrayList<AllergiesRequest> allergArray = new ArrayList<>();
        allergList.forEach(all -> {
            AllergiesRequest aRequest = new AllergiesRequest(
                    all.getAllergic_To(),
                    all.getSymptoms(),
                    all.getMedicine(),
                    all.getReports(),
                    all.getPatientId()
            );
            allergArray.add(aRequest);
        });
        return allergArray;
    }

    @Override
    public ArrayList<InjuryHistoryRequest> getInjuryData(String patientId) throws InterruptedException {
        List<InjuryHistory> injuryList = injuryHistoryRepo.findByPatientId(patientId);
        ArrayList<InjuryHistoryRequest> injuryArray = new ArrayList<>();
        injuryList.forEach(injury -> {
            InjuryHistoryRequest iRequest = new InjuryHistoryRequest(
                    injury.getInjury(),
                    injury.getDate(),
                    injury.getReports(),
                    injury.getPatientId()
            );
            injuryArray.add(iRequest);
        });
        return injuryArray;
    }

    @Override
    public List<com.example.healthe.entity.DoctorRequest> getRequestsForPatient(String patientId) {
        List<com.example.healthe.entity.DoctorRequest> patientRequests = doctorRepo.findByPatientId(patientId);
        return patientRequests;
    }

    public List<com.example.healthe.entity.DoctorRequest> getRequestsByDoctor(String doctorId) {
        List<com.example.healthe.entity.DoctorRequest> doctorRequests = doctorRepo.findByDoctorId(doctorId);
        return doctorRequests;
    }

    @Override
    public ArrayList<PatientInfoRequest> getPatientInfoByDisease(String disease) {
        List<PatientInfo> patientList = patientRepo.findByPatientsByDisease(disease);
        ArrayList<PatientInfoRequest> pList = new ArrayList<>();
        for (PatientInfo patientObject : patientList) {
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
        return pList;
    }

    @Override
    public String updateDoctorUser(String doctorId) {
        doctorUserRepo.updateDoctorStatus("Approved", doctorId);
        return "Approved";
    }

    @Override
    public List<DoctorInfo> fetchAllDoctors() {
        return doctorUserRepo.findAll();
    }

    @Override
    public String uploadFile(FileRequest uploadedFile) {
        String result = "";
        try {
            result = saveFile(uploadedFile);
            if (result == "Success") {
                //save to DB
                fileRepo.save(new File(
                                    uploadedFile.getExtension(),
                                    uploadedFile.getUploadDate(),
                                    uploadedFile.getFileName(),
                                    uploadedFile.getPatientId()
                        ));
            } else {
                result = "Error uploading file";
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public String saveFile(FileRequest uploadedFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        MultipartFile file = uploadedFile.getFile();
        String fileName = uploadedFile.getFileName();
//        String path = "classpath:";
        java.io.File newFile = new java.io.File(fileName);
        try {
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Success";
    }

    @Override
    public String updateRequestStatus(UpdateDoctorRequest docRequest) {
        doctorRepo.updateStatus(docRequest.getStatus(), docRequest.getRequestId());
        return docRequest.getStatus();
    }


}
