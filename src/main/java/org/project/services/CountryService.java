package org.project.services;

import org.project.dao.CountryDAO;
import org.project.entities.City;

import java.util.List;

public class CountryService {
    private static final CountryService instance = CountryService.getInstance();
    private CountryDAO countryDAO;
    private CountryService(CountryDAO countryDAO) {
    this.countryDAO=countryDAO;
    }
    public static CountryService getInstance() {
        return instance;
    }
    public List<City> getAllCitiesFromCountry(int id) {
       return countryDAO.citiesByCountry(id);
    }
}
