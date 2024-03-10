package org.project.services;

import org.project.dao.CountryLanguageDAO;
import org.project.entities.CountryLanguage;
import org.project.exceptions.CountryLanguageNotFoundException;
import org.slf4j.*;
import java.util.*;

public class CountryLanguageService {
    private static final CountryLanguageService INSTANCE = new CountryLanguageService();
    private final Logger logger = LoggerFactory.getLogger(CountryLanguage.class);
    private CountryLanguageDAO countryLanguageDAO=CountryLanguageDAO.getInstance();

    private CountryLanguageService() {
    }

    public static CountryLanguageService getInstance() {
        return INSTANCE;
    }

    public CountryLanguage getById(int id) {
        return countryLanguageDAO.findById(id).orElseThrow(() ->
                new CountryLanguageNotFoundException(String.format("CountryLanguage with id %d not found", id)));
    }

    public List<CountryLanguage> getAll() {
        return countryLanguageDAO.findAll();
    }

    public boolean remove(CountryLanguage countryLanguage) {
        Optional<CountryLanguage> maybeCountryLanguage = countryLanguageDAO.findById(countryLanguage.getId());
        if (maybeCountryLanguage.isPresent()) {
            countryLanguageDAO.delete(maybeCountryLanguage.get());
            logger.info(String.format("CountryLanguage %s was deleted", maybeCountryLanguage.get().getLanguage()));
            return true;
        }
        return false;
    }

    public void create(CountryLanguage countryLanguage) {
        countryLanguageDAO.create(countryLanguage);
    }

    public CountryLanguage update(CountryLanguage countryLanguage) {
        return countryLanguageDAO.update(countryLanguage);
    }
}
