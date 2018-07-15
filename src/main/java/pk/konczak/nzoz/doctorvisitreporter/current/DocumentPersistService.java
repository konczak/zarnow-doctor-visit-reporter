package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocumentPersistService {
    public void persistToFile(final Document document) throws Exception {
        // Make a transformer factory to create the Transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // Make the Transformer
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Mark the document as a DOM (XML) source
        DOMSource source = new DOMSource(document);

        Path path = getOutputFilePath();

        // Say where we want the XML to go
        StreamResult result = new StreamResult(path.toFile());
        // Write the XML to file
        transformer.transform(source, result);

        log.info("Saved XML document to file <{}>", path);
    }

    private Path getOutputFilePath() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

        String nowObfuscated = now.format(formatter);

        return Paths.get("outputs/komunikat-" + nowObfuscated + ".xml");
    }
}
