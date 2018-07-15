package pk.konczak.nzoz.doctorvisitreporter.current;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class EwusRepository {
    public Optional<Ewus> findOne(final String pesel, final LocalDate visitAt) {
        LocalTime now = LocalTime.now();

        Ewus ewus = Ewus.builder()
                .id(UUID.randomUUID().toString())
                .pesel(pesel)
                .checkedAt(visitAt.atTime(now))
                .status("UBEZPIECZONY")
                .build();

        return Optional.of(ewus);
    }
}
