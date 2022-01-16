package backend.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CSVParser {

    public static void parser(String filename) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                System.out.println(lineInArray[0] + lineInArray[1] + "etc...");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
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

    public static void printFile(File file) {

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
