package com.rpajdak.genderdetector.dao;

import java.util.Scanner;

public interface NamesDAO {

    String getAllFemaleNames();

    String geAllMaleNames();

    Scanner getScannerOfFemaleNames();

    Scanner getScannerOfMalesNames();
}
