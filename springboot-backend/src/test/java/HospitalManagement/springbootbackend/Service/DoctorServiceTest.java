package HospitalManagement.springbootbackend.Service;

import HospitalManagement.springbootbackend.Controller.DoctorController;
import HospitalManagement.springbootbackend.Exception.ResourceNotFoundException;
import HospitalManagement.springbootbackend.Model.Doctor;
import HospitalManagement.springbootbackend.Repository.DoctorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorServiceTest {

    @Autowired
    DoctorService doctorService;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    DoctorRepository doctorRepository;

    Doctor doctor;

    @Before
    public void setUp() {
        doctor= new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Ram Chand Roy");
        doctor.setAge(50);
        doctor.setGender("Male");
        doctor.setSpecialist("Cardio");
        doctor.setNoOfPatients(1);

        Doctor doctor1= new Doctor();
        doctor1.setId(2L);
        doctor1.setName("Dr. Radha Sharma");
        doctor1.setAge(40);
        doctor1.setGender("Female");
        doctor1.setSpecialist("Skin");
        doctor1.setNoOfPatients(1);

        List<Doctor> allDoctors = new ArrayList(){
            {
                add(doctor);
                add(doctor1);
            }
        };

        //By doctor Name
        Mockito.when(doctorRepository.findByName(doctor.getName()))
                .thenReturn(doctor);
        Mockito.when(doctorRepository.findByName("Dr. Radha Sharma"))
                .thenThrow(new ResourceNotFoundException("Test Not Found"))
                .thenReturn(null);

        //By doctor id
        Mockito.when(doctorRepository.findById(doctor.getId()))
                .thenReturn(java.util.Optional.ofNullable(doctor));

    }

    @Test
    public void getAllDoctorsTest() {
        List allDoctors = doctorService.getAllDoctors();
        Assert.assertNotNull(allDoctors);

    }


    @Test
    public void getDoctorByNameTest() {
        //whenValidName_thenDoctorShouldBeFound
        String name = "Dr. Ram Chand Roy";
        Doctor found = doctorService.getDoctorByName(name);
        Assert.assertEquals(found,doctor);

        //whenValidNameNotFound_thenDoctorShouldNotBeFound
        name = "Dr. Radha Sharma";
        try {
            found = doctorService.getDoctorByName(name);
            Assert.assertEquals(found, null);
        }
        catch(ResourceNotFoundException e){

        }
    }

    @Test
    public void getDoctorByIdTest() {
        //whenValidId_thenDoctorShouldBeFound
        Long id = 1L;
        Doctor found = doctorService.getDoctorById(id);
        Assert.assertEquals(found,doctor);

    }

    @Test
    public void saveDoctorTest(){

        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
        Assert.assertNotNull(doctorService.createDoctor(doctor));

    }

    @Test
    public void updateDoctorTest() {


//        Mockito.doNothing().when(doctorRepository).updateSkill(skill);
//        Assert.assertNotNull(doctorService.updateDoctorById(doctor.getId(),doctor));
        doctor.setName("Dr. Mohan Arora");
        doctor.setGender("Male");

        // when -  action or the behaviour that we are going test
        Doctor updatedDoctor = doctorService.updateDoctorById(doctor.getId(),doctor);

        // then - verify the output
        Assert.assertEquals(updatedDoctor.getName(),("Dr. Mohan Arora"));
        Assert.assertEquals(updatedDoctor.getGender(),("Male"));
    }
//
    @Test
    public void deleteDoctorTest() {

        //when  skill id = 1
        Mockito.doNothing().when(doctorRepository).delete(doctor);
        Assert.assertNotNull(doctorService.deleteDoctor(doctor.getId()));


    }

    @Test
    public void updatePatientVisitedByDoctorTest(){
        int actualNoOfPatients = doctor.getNoOfPatients()+1;
        Doctor expectedDoctor = doctorService.updatePatientVisitedByDoctor(doctor.getName());
        Assert.assertEquals(expectedDoctor.getNoOfPatients(),actualNoOfPatients);
    }
}
