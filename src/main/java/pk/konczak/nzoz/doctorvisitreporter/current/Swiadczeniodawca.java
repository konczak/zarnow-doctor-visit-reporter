package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Swiadczeniodawca {
    private String typIdSwiadczeniodawcy;
    private String idSwadczeniodawcy;
    private String idInstancji;
}
