package com.rpajdak.genderdetector.service;

import com.rpajdak.genderdetector.dao.NamesDAO;
import com.rpajdak.genderdetector.gender.Gender;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Scanner;

import static com.rpajdak.genderdetector.gender.Gender.FEMALE;
import static com.rpajdak.genderdetector.gender.Gender.MALE;

@Service
public class GendersService {

    private final NamesDAO NAMESDAO;

    public GendersService(NamesDAO NamesFromFileDAO) {
        this.NAMESDAO = NamesFromFileDAO;

    }

    public String getAllFemaleNames() {
        return NAMESDAO.getAllFemaleNames();
    }

    public String getAllMaleNames() {
        return NAMESDAO.geAllMaleNames();
    }

    public Gender getGender(String name, String variant) {
        switch (variant) {
            case "first":
                int firstNameIndex = 0;
                String firstName;
                firstName = name.split(" ")[firstNameIndex];
                return checkNameForGender(firstName);
            case "all":
                return checkMoreThenFirstName(name);

        }
        return Gender.INCONCLUSIVE;
    }

    public Gender checkNameForGender(String name) {

        Scanner femaleNamesScanner = NAMESDAO.getScannerOfFemaleNames();
        Scanner maleNameScanner = NAMESDAO.getScannerOfMalesNames();

        while (femaleNamesScanner.hasNextLine()) {
            if (femaleNamesScanner.nextLine().equals(name)) {
                return FEMALE;
            }
        }

        while (maleNameScanner.hasNextLine()) {
            if (maleNameScanner.nextLine().equals(name)) {
                return Gender.MALE;
            }
        }

        return Gender.INCONCLUSIVE;
    }

    private Gender checkMoreThenFirstName(String inputName) {

        int numberOfMales = 0;
        int numberOfFemales = 0;

        String[] names = inputName.split(" ");

        for (String name : names) {
            Gender gender = checkNameForGender(name);
            if (gender.equals(FEMALE)) {
                numberOfFemales++;
            } else if (gender.equals(MALE)) {
                numberOfMales++;
            }

        }

        if (numberOfMales == numberOfFemales) {
            return Gender.INCONCLUSIVE;
        } else {
            return numberOfMales > numberOfFemales ? Gender.MALE : FEMALE;
        }

    }
}
