package com.rpajdak.genderdetector.dao;

import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    private String getAllNamesFromFile(String filePath) {
        StringBuilder names = new StringBuilder();

        try {
            inputStream = new FileInputStream(filePath);
            scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                names.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return names.toString();
    }

}
