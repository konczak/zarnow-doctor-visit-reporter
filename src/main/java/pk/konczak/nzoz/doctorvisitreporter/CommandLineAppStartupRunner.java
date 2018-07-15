package pk.konczak.nzoz.doctorvisitreporter;

import lombok.extern.slf4j.Slf4j;
import pk.konczak.nzoz.doctorvisitreporter.current.ExecutionFacade;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandLineAppStartupRunner
        implements CommandLineRunner {

    private final ExecutionFacade executionFacade;

    CommandLineAppStartupRunner(final ExecutionFacade executionFacade) {
        this.executionFacade = executionFacade;
    }

    @Override
    public void run(String... args) {
        log.info("Hello!");

        try {
            executionFacade.execute("etc/input.csv");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        log.info("Bye bye");
    }
}
