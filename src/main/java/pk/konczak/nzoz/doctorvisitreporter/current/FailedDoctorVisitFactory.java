package pk.konczak.nzoz.doctorvisitreporter.current;

import org.springframework.stereotype.Component;

@Component
public class FailedDoctorVisitFactory {
    public FailedDoctorVisit create(final PatientVisitRecord patientVisitRecord, final Throwable e) {
        return new FailedDoctorVisit(patientVisitRecord, e, e.getMessage());
    }
}
