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
        var countries = getAll();
        if (countries.contains(country)) {
            logger.info(String.format("Country %s was save to db", country.getName()));
        } else {
            logger.info(String.format("Something goes wrong. Country %s wasn't added to db", country.getName()));
        }
    }

    public Country update(Country country) {
        var updatedCountry = countryDAO.update(country);
        if (country.equals(updatedCountry)) logger.info(String.format("Country %s wasn't update", country.getName()));
        else logger.info(String.format("Country %s was update", country.getName()));
        return updatedCountry;
    }

    public Country getCountryByCity(City city) {
        var countryByCity = countryDAO.getCountryByCity(city.getName());
        logger.info(String.format("Method getCountryByCity returns country %s", countryByCity.getName()));
        return countryByCity;
    }
}
