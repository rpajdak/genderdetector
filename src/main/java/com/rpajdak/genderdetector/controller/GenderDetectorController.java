package com.rpajdak.genderdetector.controller;

import com.rpajdak.genderdetector.gender.Gender;
import com.rpajdak.genderdetector.service.NamesService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "gender/{variant}/{name}")
    @ResponseStatus(OK)
    @ResponseBody
    public Gender getGender(@PathVariable("name") String name, @PathVariable("variant") String variant) {
        return namesService.getGender(name, variant);
    }
}
