package org.project;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.project.services.CityService;
import org.project.services.CountryLanguageService;
import org.project.services.CountryService;
import org.project.services.RedisService;
import redis.clients.jedis.Jedis;

public class App {
    private static SessionFactory mySqlSessionFactory;
    private static CountryService countryService;
    private static CityService cityService;
    private static CountryLanguageService countryLanguageService;
    private static ObjectMapper objectMapper;
    private static Jedis jedis;

    public App() {
        mySqlSessionFactory = HibernateUtil.getMySqlSessionFactory();
        countryService = CountryService.getInstance();
        cityService = CityService.getInstance();
        countryLanguageService = CountryLanguageService.getInstance();
        objectMapper = new ObjectMapper();
        jedis = new Jedis("localhost", 6379);
    }

    public static void main(String[] args) {
        App app = new App();
        var startSql = System.currentTimeMillis();
        cityService.getAll();
        long timeForGetDataFromSql = System.currentTimeMillis() - startSql;
        var startRedis = System.currentTimeMillis();
        RedisService.getInstance().getDataFromRedis(objectMapper, jedis);
        long timeForGetDataFromRedis = System.currentTimeMillis() - startRedis;
        System.out.println(timeForGetDataFromSql);
        System.out.println(timeForGetDataFromRedis);
    }
}
