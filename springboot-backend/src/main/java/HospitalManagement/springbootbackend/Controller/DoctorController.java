package HospitalManagement.springbootbackend.Controller;


import HospitalManagement.springbootbackend.Exception.ResourceNotFoundException;
import HospitalManagement.springbootbackend.Model.Doctor;
import HospitalManagement.springbootbackend.Repository.DoctorRepository;
import HospitalManagement.springbootbackend.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.createDoctor(doctor));
    }

    // build get Doctor by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable long id){
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    // build get Doctor by name REST API
    @GetMapping("/name")
    public ResponseEntity<Doctor> getDoctorByName(@RequestParam String name){
        return new ResponseEntity<Doctor>(doctorService.getDoctorByName(name),HttpStatus.OK);
    }

    // build update Doctor REST API
//    @PutMapping("/patientvisited")
//    public ResponseEntity<Doctor> updatePatientVisitedByDoctor(@RequestParam String name) {
//        Doctor updateDoctor = doctorService.updatePatientVisitedByDoctor(name);
//        return ResponseEntity.ok(updateDoctor);
//    }


    // build update Doctor REST API
    @PutMapping("{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable long id,@RequestBody Doctor doctorDetails) {
        Doctor updateDoctor = doctorService.updateDoctorById(id,doctorDetails);
        return ResponseEntity.ok(updateDoctor);
    }

    // build delete Doctor REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable long id){

        Doctor d = doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}

