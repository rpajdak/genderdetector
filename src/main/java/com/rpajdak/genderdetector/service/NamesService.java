package com.rpajdak.genderdetector.service;

import com.rpajdak.genderdetector.dao.NamesDAO;
import com.rpajdak.genderdetector.gender.Gender;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class NamesService {

    private final NamesDAO NAMESDAO;

    public NamesService(NamesDAO NamesFromFileDAO) {
        this.NAMESDAO = NamesFromFileDAO;

    }

    public String getAllFemaleNames() {
        return NAMESDAO.getAllFemaleNames();
    }

    public String getAllMaleNames() {
        return NAMESDAO.geAllMaleNames();
    }

    public Gender getGender(String name, String variant) {
        String firstName;
        switch (variant) {
            case "first":
                firstName = name.split(" ")[0];
                return checkNameForGender(firstName);
            case "all":
                return checkMoreThenFirstName(name);
        }
        return Gender.INCONCLUSIVE;
    }

    private Gender checkNameForGender(String firstName) {

        Scanner femaleNamesScanner = NAMESDAO.getScannerOfFemaleNames();
        Scanner maleNameScanner = NAMESDAO.getScannerOfMalesNames();

        while (femaleNamesScanner.hasNextLine()) {
            if (femaleNamesScanner.nextLine().equals(firstName)) {
                femaleNamesScanner.close();
                return Gender.FEMALE;
            } else {
                while (maleNameScanner.hasNextLine()) {
                    if (maleNameScanner.nextLine().equals(firstName)) {
                        maleNameScanner.close();
                        return Gender.MALE;
                    }
                }
            }
        }
        return Gender.INCONCLUSIVE;
    }

    private Gender checkMoreThenFirstName(String inputName) {

        int numberOfMales = 0;
        int numberOfFemales = 0;

        String[] names = inputName.split(" ");

        for (String name : names) {
            if (checkNameForGender(name).equals(Gender.MALE)) {
                numberOfMales++;
            } else if (checkNameForGender(name).equals(Gender.FEMALE)) {
                numberOfFemales++;
            }
        }


        if (numberOfMales == numberOfFemales) {
            return Gender.INCONCLUSIVE;
        } else {
            return numberOfMales > numberOfFemales ? Gender.MALE : Gender.FEMALE;
        }

    }
}
