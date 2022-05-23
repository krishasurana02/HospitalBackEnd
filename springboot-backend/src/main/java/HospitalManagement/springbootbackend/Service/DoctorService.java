package HospitalManagement.springbootbackend.Service;

import HospitalManagement.springbootbackend.Exception.ResourceNotFoundException;
import HospitalManagement.springbootbackend.Model.Doctor;
import HospitalManagement.springbootbackend.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors()
    {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor( Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById( long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id:" + id));
        return doctor;
    }

    public Doctor getDoctorByName( String name){
        return doctorRepository.findByName(name);
    }

    public Doctor updatePatientVisitedByDoctor( String name) {
        Doctor updateDoctor = doctorRepository.findByName(name);
        int updateValue = updateDoctor.getNoOfPatients()+1;
        updateDoctor.setNoOfPatients(updateValue);
        doctorRepository.save(updateDoctor);
        return updateDoctor;
    }

    public Doctor updateDoctorById( long id, Doctor doctorDetails) {
        Doctor updateDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id: " + id));

        updateDoctor.setName(doctorDetails.getName());
        updateDoctor.setAge(doctorDetails.getAge());
        updateDoctor.setGender(doctorDetails.getGender());
        updateDoctor.setSpecialist(doctorDetails.getSpecialist());
        updateDoctor.setNoOfPatients(doctorDetails.getNoOfPatients());

        doctorRepository.save(updateDoctor);

        return updateDoctor;
    }


    public Doctor deleteDoctor( long id){

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id: " + id));
        doctorRepository.delete(doctor);
        return doctor;
    }

}
