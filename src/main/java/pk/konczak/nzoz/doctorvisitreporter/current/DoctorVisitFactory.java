package pk.konczak.nzoz.doctorvisitreporter.current;

import org.springframework.stereotype.Component;

@Component
public class DoctorVisitFactory {
    public DoctorVisit create(final PatientVisitRecord patientVisitRecord, final Ewus ewus, final Doctor doctor) {
        Patient patient = Patient.builder()
                .pesel(patientVisitRecord.getPesel())
                .firstName("Piko")
                .lastName("Kopik")
                .build();

        return DoctorVisit.builder().patient(patient)
                .ewus(ewus)
                .doctor(doctor)
                .build();
    }
}
