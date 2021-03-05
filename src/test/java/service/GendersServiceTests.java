package service;

import com.rpajdak.genderdetector.dao.NamesFromFileDAO;
import com.rpajdak.genderdetector.gender.Gender;
import com.rpajdak.genderdetector.service.GendersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.mockito.Mockito.when;

public class GendersServiceTests {

    @InjectMocks
    GendersService gendersService;

    @Mock
    NamesFromFileDAO namesFromFileDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(namesFromFileDAO.getAllFemaleNames()).thenReturn(prepareMockFemaleNames());
        when(namesFromFileDAO.geAllMaleNames()).thenReturn(prepareMockMaleNames());
        when(namesFromFileDAO.getScannerOfFemaleNames()).thenReturn(prepareMockScanner("src/test/java/resources/testFemaleNames.txt"));
        when(namesFromFileDAO.getScannerOfMalesNames()).thenReturn(prepareMockScanner("src/test/java/resources/testMaleNames.txt"));
    }


    @Test
    public void should_return_string_of_female_names() {
        //when:
        String femaleNames = gendersService.getAllFemaleNames();

        //then:
        Assertions.assertEquals("Ada", femaleNames.split(" ")[0]);
        Assertions.assertEquals("Adria", femaleNames.split(" ")[4]);

    }


    @Test
    public void should_return_string_of_male_names() {
        //when:
        String maleNames = gendersService.getAllMaleNames();

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
        Assertions.assertEquals(Gender.FEMALE, gendersService.getGender(femaleName, variant));
    }


    @Test
    public void should_return_male_when_name_is_male_and_variant_is_first() {
        //when:
        String maleName = "Konrad";
        String variant = "first";

        //then:
        Assertions.assertEquals(Gender.MALE, gendersService.getGender(maleName, variant));
    }

    @Test
    public void should_return_inconclusive_when_name_is_inconclusive_and_variant_is_first() {
        //when:
        String maleName = "Nowa";
        String variant = "first";

        //then:
        Assertions.assertEquals(Gender.INCONCLUSIVE, gendersService.getGender(maleName, variant));
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
