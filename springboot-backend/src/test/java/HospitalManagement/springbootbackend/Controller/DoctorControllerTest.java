package HospitalManagement.springbootbackend.Controller;

import HospitalManagement.springbootbackend.Model.Doctor;
import HospitalManagement.springbootbackend.Repository.DoctorRepository;
import HospitalManagement.springbootbackend.Service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerTest {

    private MockMvc mvc;

    @Autowired
    DoctorController doctorController;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    DoctorService doctorService;

    Doctor doctor;

    @Before
    public void setUp(){
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
        doctor= new Doctor();
		doctor.setName("Dr. Ram Chand Roy");
		doctor.setAge(50);
		doctor.setGender("Male");
		doctor.setSpecialist("Cardio");
		doctor.setNoOfPatients(1);

		Doctor doctor1= new Doctor();
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

        //getAllDoctors
        Mockito.when(doctorService.getAllDoctors()).thenReturn(allDoctors);

    }

    @Test
    public void getAllDoctorsTest() throws Exception{
        this.mvc.perform(get("http://localhost:8080/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    public void getDoctorByIdTest() throws Exception{

        //get doctor by id- doctor found
        Mockito.when(doctorService.getDoctorById(doctor.getId())).thenReturn(doctor);
        this.mvc.perform(get("http://localhost:8080/doctors/"+doctor.getId()))
                .andExpect(status().isOk());

    }
    @Test
    public void saveDoctorTest() throws Exception {

        //save doctor
        Mockito.when(doctorService.createDoctor(doctor)).thenReturn(doctor);
        this.mvc.perform(post("http://localhost:8080/doctors")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(doctor))
        ).andExpect(status().isOk());

    }


    @Test
    public void getDoctorByNameTest() throws Exception{

        //get doctor by name- doctor found
        Mockito.when(doctorService.getDoctorByName(doctor.getName())).thenReturn(doctor);
        this.mvc.perform(get("http://localhost:8080/doctors/name/")
                .param("name",doctor.getName()))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteDoctorTest() throws Exception{

        //delete doctor - doctor deleted
        this.mvc.perform(delete("http://localhost:8080/doctors/"+doctor.getId()))
                .andExpect(status().isNoContent());

    }

    @Test
    public void updateDoctorTest() throws Exception {

        //update doctor
        this.mvc.perform(put("http://localhost:8080/doctors/"+doctor.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(doctor))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isOk());


    }



}
