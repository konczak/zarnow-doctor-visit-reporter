package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class AdditionalDataLoadResult {

    @Singular
    private List<DoctorVisit> doctorVisits;
    @Singular
    private List<FailedDoctorVisit> failedDoctorVisits;

}
