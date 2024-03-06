package org.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.project.dao.*;

/**
 * Hello world!
 */
public class App {
    private static SessionFactory mySqlSessionFactory;
    private static CountryDAO countryDAO;
    private static CityDAO cityDAO;
    private static CountryLanguageDAO countryLanguageDAO;
    private static ObjectMapper objectMapper;

    public App() {
        mySqlSessionFactory = HibernateUtil.getMySqlSessionFactory();
        countryDAO = new CountryDAO(mySqlSessionFactory);
        cityDAO = new CityDAO(mySqlSessionFactory);
        countryLanguageDAO = new CountryLanguageDAO(mySqlSessionFactory);
        objectMapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        App app = new App();
        // todo compare speed with redis and sql
    }
}
