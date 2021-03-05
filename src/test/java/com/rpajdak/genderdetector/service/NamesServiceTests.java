package com.rpajdak.genderdetector.service;

import com.rpajdak.genderdetector.dao.NamesFromFileDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;

public class NamesServiceTests {

    @InjectMocks
    NamesService namesService;

    @Mock
    NamesFromFileDAO namesFromFileDAO;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        given(namesFromFileDAO.getAllFemaleNames()).willReturn(prepareMockFemaleNames());

    }


    @Test
    public void should_return_string_of_females_names(){
        //when:
        String femaleNames = namesService.getAllFemaleNames();

        //then:
        Assertions.assertEquals("Ada",femaleNames.split(" ")[0]);
        Assertions.assertEquals("Adria",femaleNames.split(" ")[4]);

    }





    private String prepareMockFemaleNames() {
        return "Ada Adamina Adela Adelajda Adria";
    }


}
