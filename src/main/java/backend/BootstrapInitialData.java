package backend;

import backend.domain.Farm;
import backend.repository.FarmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

import static backend.util.CSVParser.parser;

@Component
public class BootstrapInitialData implements CommandLineRunner {

    private final FarmRepository farmRepository;

    public BootstrapInitialData(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public void run(String... args) {
        File dir = new File("src/main/resources");
        File[] dataFiles = dir.listFiles((dir1, name) -> name.endsWith("csv"));
        assert dataFiles != null;
        for (File farmDataFile : dataFiles) {
            Farm farm = parser(farmDataFile);
            assert !farm.getData().isEmpty();
            farmRepository.save(parser(farmDataFile));
            System.out.println("Parsed file: " + farmDataFile.getName());
        }
    }


}
