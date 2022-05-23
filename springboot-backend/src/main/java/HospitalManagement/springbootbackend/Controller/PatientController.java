package HospitalManagement.springbootbackend.Controller;

import HospitalManagement.springbootbackend.Exception.ResourceNotFoundException;
import HospitalManagement.springbootbackend.Model.Doctor;
import HospitalManagement.springbootbackend.Model.Patient;
import HospitalManagement.springbootbackend.Repository.PatientRepository;
import HospitalManagement.springbootbackend.Service.DoctorService;
import HospitalManagement.springbootbackend.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Patient>>getAllPatients()
    {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    // build get Patient by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable  long id){
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }


    // build update Patient REST API
    @PutMapping("{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable long id, @RequestBody Patient patientDetails) {
        Patient updatePatient = patientService.updatePatientById(id,patientDetails);
        return ResponseEntity.ok(updatePatient);
    }

    // build delete Patient REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable long id){

        Patient p= patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
