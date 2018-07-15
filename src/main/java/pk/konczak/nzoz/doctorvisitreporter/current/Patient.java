package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Patient {
    private String pesel;
    private String firstName;
    private String lastName;
}
