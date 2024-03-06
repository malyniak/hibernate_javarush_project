package org.project.dao;

import org.hibernate.SessionFactory;
import org.project.entities.Country;

public class CountryDAO extends AbstractDAO<Country, Integer> {
    private final SessionFactory sessionFactory;
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class);
        this.sessionFactory=sessionFactory;
    }
    public Country getCountryByCity(String city) {
        Country country=null;
        try(var session = sessionFactory.openSession()) {
            var query = session.createQuery("select c from Country as c where c.name=:city", Country.class);
            query.setParameter("city", city);
            country=query.uniqueResult();
        }
        return country;
    }
}
