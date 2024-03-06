package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.project.HibernateUtil;
import org.project.dao.CityDAO;
import org.project.entities.City;
import org.project.redis.CountryCity;
import org.project.services.CityService;
import redis.clients.jedis.Jedis;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class RedisTest {
    private SessionFactory sessionFactory= HibernateUtil.getMySqlSessionFactory();
    private CityDAO cityDAO=new CityDAO(sessionFactory);
    private ObjectMapper objectMapper=new ObjectMapper();
    @Test
    @Disabled
    public void testRedisData() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/0");
        var objectMapper = new ObjectMapper();
        List<Integer> ids = List.of(1, 122, 2, 547, 1021, 625, 878);
         try (var connect = redisClient.connect()) {
            var sync = connect.sync();
            for(Integer id : ids) {
                var val = sync.get(id.toString());
                var countryCity = objectMapper.readValue(val, CountryCity.class);
                System.out.println(countryCity);
            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testIncrementRequests() {
        int cashingValue=10;
        int randomId=100;
        var cityService = new CityService(cityDAO);
        try(var session = sessionFactory.openSession();) {
            var transaction = session.beginTransaction();
            for(int i=0; i<cashingValue; i++) {
                cityService.getById(randomId);
            }
            transaction.commit();
        }
        assertTrue(cityService.getCountRequest().get(100)==cashingValue);
    }
    @Test
    void getDataFromRedis() {
        Jedis jedis = new Jedis("localhost", 6379);
        int presentId=2;

        CountryCity countryCity=null;
        try {
            countryCity  = new ObjectMapper().readValue(jedis.get(String.valueOf(presentId)), CountryCity.class);
            System.out.println(countryCity);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(countryCity);
    }

}
