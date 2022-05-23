package HospitalManagement.springbootbackend;

import HospitalManagement.springbootbackend.Model.Doctor;
import HospitalManagement.springbootbackend.Model.Patient;
import HospitalManagement.springbootbackend.Repository.DoctorRepository;
import HospitalManagement.springbootbackend.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

//	@Autowired
//	private DoctorRepository doctorRepository;
//	@Autowired
//	private PatientRepository patientRepository;

	public void run(String... args) throws Exception {
//		Doctor doctor= new Doctor();
//		doctor.setName("Dr. Ram Chand Roy");
//		doctor.setAge(50);
//		doctor.setGender("Male");
//		doctor.setSpecialist("Cardio");
//		doctor.setNoOfPatients(1);
//		doctorRepository.save(doctor);
//
//		Doctor doctor1= new Doctor();
//		doctor1.setName("Dr. Radha Sharma");
//		doctor1.setAge(40);
//		doctor1.setGender("Female");
//		doctor1.setSpecialist("Skin");
//		doctor1.setNoOfPatients(1);
//		doctorRepository.save(doctor1);
//
//		Patient patient = new Patient();
//		patient.setName("Krisha");
//		patient.setVisitedDoctor("Dr. Ram Chand Roy");
//		patient.setDate_Of_Visit("2022-05-19");
//		patientRepository.save(patient);
//
//		Patient patient1 = new Patient();
//		patient1.setName("Daksh");
//		patient1.setVisitedDoctor("Dr. Radha Sharma");
//		patient1.setDate_Of_Visit("2022-04-12");
//		patientRepository.save(patient1);

	}

}
