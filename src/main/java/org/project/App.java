package org.project;

import org.hibernate.SessionFactory;
import org.project.dao.CityDAO;
import org.project.dao.CountryDAO;
import org.project.entities.City;
import org.project.entities.Country;

/**
 * Hello world!
 *
 */
public class App {
    private SessionFactory sessionFactory;
    private CountryDAO countryDAO;
    private CityDAO cityDAO;

    public App() {
        sessionFactory=new SessionFactoryImpl().getSessionFactory();
        countryDAO=new CountryDAO(Country.class);
        cityDAO=new CityDAO(City.class);
    }
    public static void main(String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
