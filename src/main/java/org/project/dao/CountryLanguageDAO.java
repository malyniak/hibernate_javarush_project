package org.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.entities.City;
import org.project.entities.Country;
import org.project.entities.CountryLanguage;

import java.util.List;
import java.util.Optional;

public class CountryLanguageDAO extends AbstractDAO<CountryLanguage, Integer> {
    private final SessionFactory sessionFactory;
    public CountryLanguageDAO(SessionFactory sessionFactory) {
        super(CountryLanguage.class);
        this.sessionFactory=sessionFactory;
    }
    public List<CountryLanguage> citiesByCountry(int id) {
       Optional<Country> maybeCountry = Optional.ofNullable(sessionFactory.getCurrentSession().get(Country.class, id));
        if (maybeCountry.isPresent()) {
            Query<CountryLanguage> query = sessionFactory.getCurrentSession().createQuery("select l from CountryLanguage as l where l.country=:id", CountryLanguage.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
        throw new RuntimeException();
    }
}
