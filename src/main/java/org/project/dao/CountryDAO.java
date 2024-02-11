package org.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.entities.City;
import org.project.entities.Country;

import java.util.List;
import java.util.Optional;

public class CountryDAO extends AbstractDAO<Country, Integer> {
    private final SessionFactory sessionFactory;
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class);
        this.sessionFactory=sessionFactory;
    }
    public List<City> citiesByCountry(int id) {
        Optional<Country> maybeCountry = findById(id);
        if(maybeCountry.isPresent()) {
            Session session = sessionFactory.openSession();
            Query<City> query = session.createQuery("select c from City as c where c.country=:id", City.class);
            query.setParameter("id", id);
           return query.getResultList();
        } throw new RuntimeException();
    }
}
