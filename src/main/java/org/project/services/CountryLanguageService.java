package org.project.services;

import org.project.dao.CountryLanguageDAO;

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
