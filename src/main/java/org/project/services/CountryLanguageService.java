package org.project.services;

import org.project.dao.CountryDAO;
import org.project.dao.CountryLanguageDAO;
import org.project.entities.City;
import org.project.entities.CountryLanguage;

import java.util.List;

public class CountryLanguageService {
    private static final CountryLanguageService instance = CountryLanguageService.getInstance();
    private CountryLanguageDAO countryLanguageDAO;
    private CountryLanguageService(CountryLanguageDAO countryLanguageDAO) {
    this.countryLanguageDAO=countryLanguageDAO;
    }
    public static CountryLanguageService getInstance() {
        return instance;
    }
}
