package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import pk.konczak.nzoz.doctorvisitreporter.current.AdditionalDataLoadResult;
import pk.konczak.nzoz.doctorvisitreporter.current.DocumentFactory;
import pk.konczak.nzoz.doctorvisitreporter.current.DocumentPersistService;
import pk.konczak.nzoz.doctorvisitreporter.current.FailedDoctorVisit;
import pk.konczak.nzoz.doctorvisitreporter.current.InputDataParser;
import pk.konczak.nzoz.doctorvisitreporter.current.InputFileReader;
import pk.konczak.nzoz.doctorvisitreporter.current.Komunikat;
import pk.konczak.nzoz.doctorvisitreporter.current.KomunikatFactory;
import pk.konczak.nzoz.doctorvisitreporter.current.PatientVisitRecord;
import pk.konczak.nzoz.doctorvisitreporter.current.VisitDataCollector;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExecutionFacade {

    private final InputFileReader inputFileReader;
    private final InputDataParser inputDataParser;
    private final VisitDataCollector visitDataCollector;
    private final KomunikatFactory komunikatFactory;
    private final DocumentFactory documentFactory;
    private final DocumentPersistService documentPersistService;

    ExecutionFacade(final InputFileReader inputFileReader,
                    final InputDataParser inputDataParser,
                    final VisitDataCollector visitDataCollector,
                    final KomunikatFactory komunikatFactory,
                    final DocumentFactory documentFactory,
                    final DocumentPersistService documentPersistService) {
        this.inputFileReader = inputFileReader;
        this.inputDataParser = inputDataParser;
        this.visitDataCollector = visitDataCollector;
        this.komunikatFactory = komunikatFactory;
        this.documentFactory = documentFactory;
        this.documentPersistService = documentPersistService;
    }

    public void execute(final String inputFilePath) {
        try {
            executeUnsafe(inputFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute", e);
        }
    }

    private void executeUnsafe(final String inputFilePath) throws Exception {
        log.info("start");

        log.info("reading csv file");
        File inputFile = inputFileReader.read(inputFilePath);

        log.info("extracting records from csv");
        List<PatientVisitRecord> patientVisitRecords = inputDataParser.parse(inputFile);

        log.info("loading additional data");
        AdditionalDataLoadResult additionalDataLoadResult = visitDataCollector.loadAdditionalData(patientVisitRecords);

        for (FailedDoctorVisit failedDoctorVisit : additionalDataLoadResult.getFailedDoctorVisits()) {
            log.error("There is failed doctor visit <{}>", failedDoctorVisit);
        }

        log.info("converting list of visits to komunikat");
        Komunikat komunikat = komunikatFactory.create(additionalDataLoadResult.getDoctorVisits());

        log.info("converting komunikat to document");
        Document document = documentFactory.create(komunikat);

        log.info("persisting document to file");
        documentPersistService.persistToFile(document);

        log.info("completed");
    }
}
