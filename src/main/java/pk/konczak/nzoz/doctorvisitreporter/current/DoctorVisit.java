package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DoctorVisit {
    private Patient patient;
    private Doctor doctor;
    private Ewus ewus;
}
