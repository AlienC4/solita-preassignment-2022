package backend.util;

import backend.domain.Farm;
import backend.domain.FarmData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static @NotNull Farm parser(File file) {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] lineInArray;
            String name = "";
            List<FarmData> data = new ArrayList<>();
            while ((lineInArray = reader.readNext()) != null) {
                try {
                    name = lineInArray[0];
                    String date = lineInArray[1], type = lineInArray[2];
                    if (!NumberUtils.isCreatable(lineInArray[3])) continue;
                    double value = Double.parseDouble(lineInArray[3]);

                    // Validate that the values are within spec for each type
                    if (!FarmData.validate(type, value)) continue;
//                    System.out.printf("%s, %s, %f%n", date, type, value);
                    data.add(new FarmData(date, type, value));
                } catch (NumberFormatException e){
//                    System.err.println("");
                    e.printStackTrace();
                }
            }
            Farm farm = new Farm(data);
            farm.setName(name);
            return farm;
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return new Farm();
    }


    private static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printFile(@NotNull File file) {

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
