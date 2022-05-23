package HospitalManagement.springbootbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patients")

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;


    @Column(name = "visitedDoctor")
    private String visitedDoctor;


    @Column(name = "date_of_visit")
    private String date_Of_Visit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && name.equals(patient.name)
                && visitedDoctor.equals(patient.visitedDoctor)
                && date_Of_Visit.equals(patient.date_Of_Visit);
    }
}
