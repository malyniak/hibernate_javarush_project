package org.project;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.project.dao.CityDAO;
import org.project.dao.CountryDAO;
import org.project.dao.CountryLanguageDAO;
import redis.clients.jedis.Jedis;

import static java.util.Objects.nonNull;

/**
 * Hello world!
 *
 */
public class App {
    private static SessionFactory mySqlSessionFactory;
    private static SessionFactory redisSessionFactory;
    private static CountryDAO countryDAO;
    private static CityDAO cityDAO;
    private static CountryLanguageDAO countryLanguageDAO;
    private static RedisClient redisClient;

    public App() {
        mySqlSessionFactory=HibernateUtil.getMySqlSessionFactory();

    }
    public static void main(String[] args )
    {
        App app = new App();
        countryDAO=new CountryDAO(mySqlSessionFactory);
        cityDAO=new CityDAO(mySqlSessionFactory);
        countryLanguageDAO=new CountryLanguageDAO(mySqlSessionFactory);
        //    Session session = mySqlSessionFactory.openSession();
     //   Transaction mySqltransaction = session.beginTransaction();
      //  Country country = countryDAO.findById(5);
     //   City city = cityDAO.create(new City("Kolomyia", country, "some", 30000));
   //     session.remove(cityDAO.findById(4080));
    //    mySqltransaction.commit();
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        String developer = connection.sync().get("developer");
        System.out.println(developer);
    }
    private void shutdown() {
        if (nonNull(mySqlSessionFactory)) {
            mySqlSessionFactory.close();
        }
        if (nonNull(redisClient)) {
            redisClient.shutdown();
        }
    }
}
