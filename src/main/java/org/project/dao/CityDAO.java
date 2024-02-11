package org.project.dao;

import org.hibernate.SessionFactory;
import org.project.entities.City;

public class CityDAO extends AbstractDAO<City, Integer> {
    private SessionFactory sessionFactory;
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class);
        this.sessionFactory=sessionFactory;
    }
    public long getCitiesCount() {
       return findAll().stream().distinct().count();
    }
}
