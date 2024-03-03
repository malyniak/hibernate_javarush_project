package org.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.project.dao.CityDAO;
import org.project.dao.CountryDAO;
import org.project.dao.CountryLanguageDAO;
import org.project.redis.RedisMapper;
import redis.clients.jedis.Jedis;

import static java.util.Objects.nonNull;

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
        //todo compare speed with redis and sql
    }
}
