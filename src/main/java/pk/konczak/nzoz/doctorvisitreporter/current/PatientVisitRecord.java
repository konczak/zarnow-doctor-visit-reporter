package pk.konczak.nzoz.doctorvisitreporter.current;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientVisitRecord {
    @CsvBindByName(column = "lekarz", required = true)
    private long doctorId;

    @CsvBindByName(column = "pesel", required = true)
    private String pesel;

    @CsvCustomBindByName(column = "dataWizyty", required = true, converter = ConvertStringToDate.class)
    private LocalDate visitAt;
}
