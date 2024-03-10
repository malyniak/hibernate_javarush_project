package org.project.services;

import org.project.dao.CountryDAO;
import org.project.entities.City;
import org.project.entities.Country;
import org.project.exceptions.CountryNotFoundException;
import org.slf4j.*;
import java.util.*;

public class CountryService {
    private static final CountryService INSTANCE = new CountryService();
    private final Logger logger = LoggerFactory.getLogger(Country.class);
    private final CountryDAO countryDAO=CountryDAO.getInstance();
    private CountryService() {
    }

    public static CountryService getInstance() {
        return INSTANCE;
    }

    public Country getById(int id) {
        return countryDAO.findById(id).orElseThrow(() ->
                new CountryNotFoundException(String.format("Country with id %d not found", id)));
    }

    public List<Country> getAll() {
        return countryDAO.findAll();
    }

    public boolean remove(Country country) {
        Optional<Country> maybeCountry = countryDAO.findById(country.getId());
        if (maybeCountry.isPresent()) {
            countryDAO.delete(maybeCountry.get());
            logger.info(String.format("Country %s was deleted", maybeCountry.get().getName()));
            return true;

        }
        return false;
    }
    public void create(Country country) {
        countryDAO.create(country);
    }

    public Country update(Country country) {
        return countryDAO.update(country);
    }

    public Country getCountryByCity(City city) {
        return countryDAO.getCountryByCity(city.getName());
    }
}
