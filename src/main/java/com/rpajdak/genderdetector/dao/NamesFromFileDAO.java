package com.rpajdak.genderdetector.dao;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Scanner;

@Getter
@Repository
public class NamesFromFileDAO implements NamesDAO {

    private final String FEMALE_FILE = "/femaleNames.txt";
    private final String MALE_FILE = "/maleNames.txt";

    private InputStream inputStream = null;
    private Scanner scanner = null;

    @Override
    public String getAllFemaleNames() {
        return getAllNamesFromFile(FEMALE_FILE);
    }

    @Override
    public String geAllMaleNames() {
        return getAllNamesFromFile(MALE_FILE);
    }

    @Override
    public Scanner getScannerOfMalesNames() {
        return getScannerOfContent(MALE_FILE);
    }

    @Override
    public Scanner getScannerOfFemaleNames() {
        return getScannerOfContent(FEMALE_FILE);
    }

    public Scanner getScannerOfContent(String filePath) {

            inputStream = getClass().getResourceAsStream(filePath);
            scanner = new Scanner(inputStream);

        return scanner;
    }

    private String getAllNamesFromFile(String filePath) {
        StringBuilder names = new StringBuilder();
        scanner = getScannerOfContent(filePath);
        while (scanner.hasNextLine()) {
            names.append(scanner.nextLine()).append("\n");
        }
        return names.toString().trim();
    }


}
