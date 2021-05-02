package com.company;



import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        String dirName = args[0];
        HashMap<Date, Integer> heartRates = new HashMap<>();
        try {
            Files.list(new File(dirName).toPath())
                    .forEach(path -> {
                        if(path.toString().toLowerCase(Locale.ROOT).endsWith(".csv")) {
                            System.out.println(path);
                            getHeartRates(path, heartRates);
                        }

                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void getHeartRates(Path csvFile, HashMap heartRates) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvFile.toFile()));
            String[] values;
            int lastFullTimeStamp = 0;
            int field1 = 3;
            int value1 = 4;
            int field2 = 6;
            int value2 = 7;
            while ((values = csvReader.readNext()) != null) {
                if(values[field1].equals("timestamp")) {
                    if(Integer.parseInt(values[value1]) > 1) {
                        lastFullTimeStamp = Integer.parseInt(values[value1]);
                    }
                } else
                {
                    if(values[field2].equals("heart_rate")) {
                        System.out.println(values[value2]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
