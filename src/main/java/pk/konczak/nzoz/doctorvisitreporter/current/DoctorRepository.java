package pk.konczak.nzoz.doctorvisitreporter.current;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepository {

    private static final Map<Long, Doctor> mapOfDoctors = new HashMap<>();

    static {
        long id = 1L;
        Doctor doctor = Doctor.builder()
                .id(id)
                .firstName("John")
                .lastName("Doe")
                .numberOfLicenseToPractice("999888777")
                .build();
        mapOfDoctors.put(id, doctor);
    }

    public Optional<Doctor> findOne(final long doctorId) {
        Doctor doctor = mapOfDoctors.get(doctorId);

        return Optional.ofNullable(doctor);
    }
}
