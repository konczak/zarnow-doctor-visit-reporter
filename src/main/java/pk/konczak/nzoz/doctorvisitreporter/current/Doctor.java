package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Doctor {
    private long id;
    private String firstName;
    private String lastName;
    private String numberOfLicenseToPractice;
}
