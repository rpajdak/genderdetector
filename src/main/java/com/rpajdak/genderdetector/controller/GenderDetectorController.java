package com.rpajdak.genderdetector.controller;

import com.rpajdak.genderdetector.gender.Gender;
import com.rpajdak.genderdetector.service.GendersService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class GenderDetectorController {

    private final GendersService gendersService;

    public GenderDetectorController(GendersService gendersService) {
        this.gendersService = gendersService;
    }

    @GetMapping("names/male")
    @ResponseStatus(OK)
    public String getAllMaleNames() {
        return gendersService.getAllMaleNames();
    }


    @GetMapping("names/female")
    @ResponseStatus(OK)
    public String getAllFemaleNames() {
        return gendersService.getAllFemaleNames();
    }

    @GetMapping(value = "gender/{variant}/{name}")
    @ResponseStatus(OK)
    @ResponseBody
    public Gender getGender(@PathVariable("name") String name, @PathVariable("variant") String variant) {
        return gendersService.getGender(name, variant);
    }
}
