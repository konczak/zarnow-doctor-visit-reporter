package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class FailedDoctorVisit {
    private PatientVisitRecord patientVisitRecord;
    private Throwable exception;
    private String error;
}
