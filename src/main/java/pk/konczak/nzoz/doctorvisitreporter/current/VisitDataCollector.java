package pk.konczak.nzoz.doctorvisitreporter.current;

import pk.konczak.nzoz.doctorvisitreporter.current.AdditionalDataLoadResult;
import pk.konczak.nzoz.doctorvisitreporter.current.PatientVisitRecord;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class VisitDataCollector {

    private final EwusRepository ewusRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorVisitFactory doctorVisitFactory;
    private final FailedDoctorVisitFactory failedDoctorVisitFactory;

    VisitDataCollector(final EwusRepository ewusRepository,
                       final DoctorRepository doctorRepository,
                       final DoctorVisitFactory doctorVisitFactory,
                       final FailedDoctorVisitFactory failedDoctorVisitFactory) {
        this.ewusRepository = ewusRepository;
        this.doctorRepository = doctorRepository;
        this.doctorVisitFactory = doctorVisitFactory;
        this.failedDoctorVisitFactory = failedDoctorVisitFactory;
    }

    public AdditionalDataLoadResult loadAdditionalData(final List<PatientVisitRecord> patientVisitRecords) {
        AdditionalDataLoadResult.AdditionalDataLoadResultBuilder builder = AdditionalDataLoadResult.builder();

        for (PatientVisitRecord patientVisitRecord : patientVisitRecords) {
            Ewus ewus;
            Doctor doctor;
            final String pesel = patientVisitRecord.getPesel();
            final LocalDate visitAt = patientVisitRecord.getVisitAt();
            final long doctorId = patientVisitRecord.getDoctorId();
            try {
                ewus = ewusRepository.findOne(pesel, visitAt)
                        .orElseThrow(() -> new RuntimeException("Ewus for pesel <" + pesel + "+> and visitAt <" + visitAt + "> is missing"));

                doctor = doctorRepository.findOne(doctorId)
                        .orElseThrow(() -> new RuntimeException("Doctor with ID <" + doctorId + "> does not exists"));

                DoctorVisit doctorVisit = doctorVisitFactory.create(patientVisitRecord, ewus, doctor);

                builder.doctorVisit(doctorVisit);
            } catch (RuntimeException e) {
                FailedDoctorVisit failedDoctorVisit = failedDoctorVisitFactory.create(patientVisitRecord, e);
                builder.failedDoctorVisit(failedDoctorVisit);
            }
        }

        return builder.build();
    }
}
