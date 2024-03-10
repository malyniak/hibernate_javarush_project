package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.project.HibernateUtil;
import org.project.dao.CityDAO;
import org.project.redis.CountryCity;
import org.project.services.CityService;
import redis.clients.jedis.Jedis;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class RedisTest {
    private SessionFactory sessionFactory= HibernateUtil.getMySqlSessionFactory();
    private CityDAO cityDAO=CityDAO.getInstance();
    private ObjectMapper objectMapper=new ObjectMapper();
    private static Jedis jedis = new Jedis("localhost", 6379);
    @Test
    public void testRedisData() {
        var objectMapper = new ObjectMapper();
        List<Integer> ids = List.of(1, 122, 2, 457, 298, 353, 140);
            for(Integer id : ids) {
                var val = jedis.get(id.toString());
                CountryCity countryCity = null;
                try {
                    countryCity = objectMapper.readValue(val, CountryCity.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(countryCity);
            }
        }
    @Test
    void testIncrementRequests() {
        int cashingValue=10;
        int randomId=100;
        var cityService = CityService.getInstance();
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
