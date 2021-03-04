package com.rpajdak.genderdetector.controller;

import com.rpajdak.genderdetector.service.NamesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class GenderDetectorController {

    private final NamesService namesService;

    public GenderDetectorController(NamesService namesService) {
        this.namesService = namesService;
    }

    @GetMapping("names/male")
    @ResponseStatus(OK)
    public String getAllMaleNames() {
        return namesService.getAllMaleNames();
    }


    @GetMapping("names/female")
    @ResponseStatus(OK)
    public String getAllFemaleNames() {
        return namesService.getAllFemaleNames();
    }


}
