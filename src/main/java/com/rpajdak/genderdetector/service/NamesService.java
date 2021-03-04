package com.rpajdak.genderdetector.service;

import com.rpajdak.genderdetector.dao.NamesDAO;
import org.springframework.stereotype.Service;

@Service
public class NamesService {

    private final NamesDAO NAMESDAO;

    public NamesService(NamesDAO NamesFromFileDAO) {
        this.NAMESDAO = NamesFromFileDAO;
    }

    public String getAllFemaleNames() {
        return NAMESDAO.getAllFemaleNames();
    }

    public String getAllMaleNames(){
        return NAMESDAO.geAllMaleNames();
    }


}
