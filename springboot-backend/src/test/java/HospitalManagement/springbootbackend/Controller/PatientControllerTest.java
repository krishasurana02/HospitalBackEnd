package HospitalManagement.springbootbackend.Controller;


import HospitalManagement.springbootbackend.Model.Patient;
import HospitalManagement.springbootbackend.Model.Patient;
import HospitalManagement.springbootbackend.Service.PatientService;
import HospitalManagement.springbootbackend.Service.PatientService;
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
public class PatientControllerTest {
    
    private MockMvc mvc;

    @Autowired
    PatientController patientController;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    PatientService patientService;

    Patient patient;

    @Before
    public void setUp(){
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
        patient = new Patient();
		patient.setName("Krisha");
		patient.setVisitedDoctor("Dr. Ram Chand Roy");
		patient.setDate_Of_Visit("2022-05-19");

		Patient patient1 = new Patient();
		patient1.setName("Daksh");
		patient1.setVisitedDoctor("Dr. Radha Sharma");
		patient1.setDate_Of_Visit("2022-04-12");
		

        List<Patient> allPatients = new ArrayList(){
            {
                add(patient);
                add(patient1);
            }
        };

        //getAllPatients
        Mockito.when(patientService.getAllPatients()).thenReturn(allPatients);

    }

    @Test
    public void getAllPatientsTest() throws Exception{
        this.mvc.perform(get("http://localhost:8080/patients"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientByIdTest() throws Exception{

        //get patient by id- patient found
        Mockito.when(patientService.getPatientById(patient.getId())).thenReturn(patient);
        this.mvc.perform(get("http://localhost:8080/patients/"+patient.getId()))
                .andExpect(status().isOk());

    }
    @Test
    public void savePatientTest() throws Exception {

        //save patient
        Mockito.when(patientService.createPatient(patient)).thenReturn(patient);
        this.mvc.perform(post("http://localhost:8080/patients")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(patient))

        ).andExpect(status().isOk());

    }



    @Test
    public void deletePatientTest() throws Exception{

        //delete patient - patient deleted
        this.mvc.perform(delete("http://localhost:8080/patients/"+patient.getId()))
                .andExpect(status().isNoContent());

    }

    @Test
    public void updatePatientTest() throws Exception {

        //update patient
        this.mvc.perform(put("http://localhost:8080/patients/"+patient.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(patient))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isOk());


    }

}
