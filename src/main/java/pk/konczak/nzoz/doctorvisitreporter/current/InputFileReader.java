package pk.konczak.nzoz.doctorvisitreporter.current;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class InputFileReader {

    public File read(final String pathToFile) {
        Path path = Paths.get(pathToFile);

        File file = path.toFile();

        if (!file.exists()) {
            throw new IllegalArgumentException("File <" + pathToFile + "> does not exists");
        }

        return file;
    }
}
