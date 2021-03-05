package com.rpajdak.genderdetector.service;

import com.rpajdak.genderdetector.dao.NamesFromFileDAO;
import org.junit.jupiter.api.BeforeEach;
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








    private String prepareMockFemaleNames() {
        return "Ada Adamina Adela Adelajda Adria";
    }


}
