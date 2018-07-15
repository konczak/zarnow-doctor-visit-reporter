package pk.konczak.nzoz.doctorvisitreporter.current;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;

public class ConvertStringToDate
        extends AbstractBeanField {
    @Override
    protected LocalDate convert(final String textWithDate)
            throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return LocalDate.parse(textWithDate);
    }
}
