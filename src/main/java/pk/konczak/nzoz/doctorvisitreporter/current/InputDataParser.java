package pk.konczak.nzoz.doctorvisitreporter.current;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InputDataParser {
    public List<PatientVisitRecord> parse(final File csv) {
        CsvToBean<PatientVisitRecord> csvToBean;
        try {
            csvToBean = new CsvToBeanBuilder<PatientVisitRecord>(new FileReader(csv))
                    .withType(PatientVisitRecord.class)
                    .build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return csvToBean.parse();
    }
}
