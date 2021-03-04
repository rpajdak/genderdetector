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
        String[] names = name.split(" ");

        Scanner femaleNamesScanner = NAMESDAO.getScannerOfFemaleNames();
        Scanner maleNameScanner = NAMESDAO.getScannerOfMalesNames();

        boolean isMale = false;
        boolean isFemale = false;

        switch (variant) {
            case "first":
                String firstName = names[0];
                while (femaleNamesScanner.hasNextLine()) {
                    if (femaleNamesScanner.nextLine().equals(firstName)) {
                        return Gender.FEMALE;

                    } else {
                        while (maleNameScanner.hasNextLine()) {
                            if (maleNameScanner.nextLine().equals(firstName)) {
                                return Gender.MALE;
                            }
                        }
                    }
                }
                break;
            case "all":
                return Gender.INCONCLUSIVE;
        }
        return Gender.INCONCLUSIVE;
    }


}
