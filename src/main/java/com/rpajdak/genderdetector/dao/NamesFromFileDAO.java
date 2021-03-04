package com.rpajdak.genderdetector.dao;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Getter
@Repository
public class NamesFromFileDAO implements NamesDAO {

    private final String FEMALE_FILE = "src/main/resources/femaleNames.txt";
    private final String MALE_FILE = "src/main/resources/maleNames.txt";

    private FileInputStream inputStream = null;
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
        try {
            inputStream = new FileInputStream(filePath);
            scanner = new Scanner(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
