package org.project;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.project.entities.City;
import org.project.entities.Country;
import org.project.entities.CountryLanguage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory mySqlSessionFactory;

    public static SessionFactory getMySqlSessionFactory() {
        Properties properties = new Properties();
        try(var inputStream = Files.newInputStream(Path.of("D:\\hibernate_final_project\\src\\main\\resources\\world.properties"))) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mySqlSessionFactory = new Configuration().setProperties(properties)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(CountryLanguage.class)
                .buildSessionFactory();
        return mySqlSessionFactory;
    }
}

