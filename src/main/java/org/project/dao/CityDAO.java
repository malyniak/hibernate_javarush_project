package org.project.dao;

import org.hibernate.*;
import org.project.HibernateUtil;
import org.project.entities.City;
import java.util.List;

public class CityDAO extends AbstractDAO<City, Integer> {
    private static final CityDAO INSTANCE=new CityDAO(HibernateUtil.getMySqlSessionFactory());
    private SessionFactory sessionFactory;
    private CityDAO(SessionFactory sessionFactory) {
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
    public static CityDAO getInstance() {
        return INSTANCE;
    }
}
