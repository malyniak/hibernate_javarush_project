package org.project.dao;

import org.hibernate.SessionFactory;
import org.project.HibernateUtil;
import org.project.entities.Country;

public class CountryDAO extends AbstractDAO<Country, Integer> {
    private static final CountryDAO INSTANCE = new CountryDAO(HibernateUtil.getMySqlSessionFactory());
    private final SessionFactory sessionFactory;
    private CountryDAO(SessionFactory sessionFactory) {
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
    public static CountryDAO getInstance() {
        return INSTANCE;
    }
}
