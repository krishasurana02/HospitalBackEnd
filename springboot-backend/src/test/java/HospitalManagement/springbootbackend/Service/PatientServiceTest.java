package HospitalManagement.springbootbackend.Service;

import HospitalManagement.springbootbackend.Exception.ResourceNotFoundException;
import HospitalManagement.springbootbackend.Model.Patient;
import HospitalManagement.springbootbackend.Model.Patient;
import HospitalManagement.springbootbackend.Repository.PatientRepository;
import HospitalManagement.springbootbackend.Repository.PatientRepository;
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
public class PatientServiceTest {

    @Autowired
    PatientService patientService;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    PatientRepository patientRepository;

    Patient patient;

    @Before
    public void setUp() {
        patient= new Patient();
        patient.setId(1L);
        patient.setName("Krisha");
		patient.setVisitedDoctor("Dr. Ram Chand Roy");
		patient.setDate_Of_Visit("2022-05-19");

		Patient patient1 = new Patient();
		patient1.setId(2L);
		patient1.setName("Daksh");
		patient1.setVisitedDoctor("Dr. Radha Sharma");
		patient1.setDate_Of_Visit("2022-04-12");

        List<Patient> allPatients = new ArrayList(){
            {
                add(patient);
                add(patient1);
            }
        };

        //By patient id
        Mockito.when(patientRepository.findById(patient.getId()))
                .thenReturn(java.util.Optional.ofNullable(patient));

    }
    @Test
    public void getAllPatientsTest() {
        List allPatients = patientService.getAllPatients();
        Assert.assertNotNull(allPatients);

    }


    @Test
    public void getPatientByIdTest() {
        //whenValidId_thenPatientShouldBeFound
        Long id = 1L;
        Patient found = patientService.getPatientById(id);
        Assert.assertEquals(found,patient);

    }

    @Test
    public void savePatientTest(){

        Mockito.when(patientRepository.save(patient)).thenReturn(patient);
        Assert.assertNotNull(patientService.createPatient(patient));

    }

    @Test
    public void updatePatientTest() {


        patient.setName("Jay");
        patient.setDate_Of_Visit("2022-01-22");

        // when -  action or the behaviour that we are going test
        Patient updatedPatient = patientService.updatePatientById(patient.getId(),patient);

        // then - verify the output
        Assert.assertEquals(updatedPatient.getName(),("Jay"));
        Assert.assertEquals(updatedPatient.getDate_Of_Visit(),("2022-01-22"));
    }
    //
    @Test
    public void deletePatientTest() {

        //when  skill id = 1
        Mockito.doNothing().when(patientRepository).delete(patient);
        Assert.assertNotNull(patientService.deletePatient(patient.getId()));

    }
}
