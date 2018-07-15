package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Ewus {
    private String id;
    private String status;
    private LocalDateTime checkedAt;
    private String pesel;

}
