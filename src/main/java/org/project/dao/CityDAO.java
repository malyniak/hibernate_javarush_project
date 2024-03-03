package org.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.project.entities.City;

import java.util.List;

public class CityDAO extends AbstractDAO<City, Integer> {
    private SessionFactory sessionFactory;
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class);
        this.sessionFactory=sessionFactory;
    }
    public long getCitiesCount() {
       return findAll().stream().distinct().count();
    }
    public List<City> findAllCitiesByCountryId(Integer id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<City> query = session.createQuery("select c from City as c where country.id=:id", City.class);
            query.setParameter("id", id);
            transaction.commit();
            return query.getResultList();
        }
    }
}
