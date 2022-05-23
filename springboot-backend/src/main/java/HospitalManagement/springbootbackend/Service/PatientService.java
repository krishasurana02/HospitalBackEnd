package HospitalManagement.springbootbackend.Service;

import HospitalManagement.springbootbackend.Exception.ResourceNotFoundException;
import HospitalManagement.springbootbackend.Model.Patient;
import HospitalManagement.springbootbackend.Repository.DoctorRepository;
import HospitalManagement.springbootbackend.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorService doctorService;


    public List<Patient> getAllPatients()
    {
        return patientRepository.findAll();
    }


    public Patient createPatient( Patient patient) {
        Patient p= patientRepository.save(patient);
        doctorService.updatePatientVisitedByDoctor(p.getVisitedDoctor());
        return p;
    }



    public Patient getPatientById( long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id:" + id));
        return patient;
    }




    public Patient updatePatientById( long id,  Patient patientDetails) {
        Patient updatePatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id: " + id));

        updatePatient.setName(patientDetails.getName());
        updatePatient.setVisitedDoctor(patientDetails.getVisitedDoctor());
        updatePatient.setDate_Of_Visit(patientDetails.getDate_Of_Visit());

        patientRepository.save(updatePatient);
        return updatePatient;
    }


    public Patient deletePatient( long id){

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id: " + id));
        patientRepository.delete(patient);
        return patient;

    }
}
