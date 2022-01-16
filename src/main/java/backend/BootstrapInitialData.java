package backend;

import backend.repository.FarmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

import static backend.util.CSVParser.printFile;

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
        printFile(dataFiles[0]);
//        for (int i = 0; i < 10; i++) {
//            FarmRepository.save(new Farm(faker.name().fullName(), faker.internet().emailAddress()));
//        }
    }


}
