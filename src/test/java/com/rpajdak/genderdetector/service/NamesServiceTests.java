package com.rpajdak.genderdetector.service;

import com.rpajdak.genderdetector.dao.NamesFromFileDAO;
import com.rpajdak.genderdetector.gender.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class NamesServiceTests {

    @InjectMocks
    NamesService namesService;

    @Mock
    NamesFromFileDAO namesFromFileDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(namesFromFileDAO.getAllFemaleNames()).thenReturn(prepareMockFemaleNames());
        when(namesFromFileDAO.geAllMaleNames()).thenReturn(prepareMockMaleNames());
        when(namesFromFileDAO.getScannerOfFemaleNames()).thenReturn(prepareMockScanner("src/test/java/com/rpajdak/genderdetector/service/testFemaleNames.txt"));
        when(namesFromFileDAO.getScannerOfMalesNames()).thenReturn(prepareMockScanner("src/test/java/com/rpajdak/genderdetector/service/testMaleNames.txt"));
    }


    @Test
    public void should_return_string_of_female_names() {
        //when:
        String femaleNames = namesService.getAllFemaleNames();

        //then:
        Assertions.assertEquals("Ada", femaleNames.split(" ")[0]);
        Assertions.assertEquals("Adria", femaleNames.split(" ")[4]);

    }


    @Test
    public void should_return_string_of_male_names() {
        //when:
        String maleNames = namesService.getAllMaleNames();

        //then:
        Assertions.assertEquals("Aaron", maleNames.split(" ")[0]);
        Assertions.assertEquals("Abraham", maleNames.split(" ")[4]);

    }


    @Test
    public void should_return_female_when_name_is_female_and_variant_is_first() {
        //when:
        String femaleName = "Anna";
        String variant = "first";

        //then:
        Assertions.assertEquals(Gender.FEMALE, namesService.getGender(femaleName, variant));
    }


    @Test
    public void should_return_male_when_name_is_male_and_variant_is_first() {
        //when:
        String maleName = "Konrad";
        String variant = "first";

        //then:
        Assertions.assertEquals(Gender.MALE, namesService.getGender(maleName, variant));
    }


    private String prepareMockFemaleNames() {
        return "Ada Adamina Adela Adelajda Adria";
    }

    private String prepareMockMaleNames() {
        return "Aaron Abdon Abel Abelard Abraham";
    }


    private Scanner prepareMockScanner(String filePath) {
        Scanner scanner = null;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            scanner = new Scanner(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scanner;
    }

}
