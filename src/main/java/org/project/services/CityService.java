package org.project.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.project.HibernateUtil;
import org.project.dao.CityDAO;
import org.project.entities.City;
import org.project.redis.CountryCity;
import org.project.redis.RedisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.*;

public class CityService {
    private final Logger logger = LoggerFactory.getLogger(City.class);
    private SessionFactory sessionFactory = HibernateUtil.getMySqlSessionFactory();
    private CityDAO cityDAO;
    private Jedis jedis = new Jedis("localhost", 6379);

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    private final Map<Integer, Integer> countRequests = new HashMap<>();

    public boolean delete(Integer id) {
        Optional<City> maybeCity = cityDAO.findById(id);
        if (maybeCity.isPresent()) {
            cityDAO.delete(maybeCity.get());
            logger.info(String.format("City %s was deleted", maybeCity.get().getName()));
            return true;

        }
        return false;
    }

    public long getCitiesCount() {
        var citiesCount = cityDAO.getCitiesCount();
        logger.info(String.format("There are %d cities", citiesCount));
        return citiesCount;
    }

    public City getById(Integer id) {

        City city = null;
        try {
            city = countRequests.get(id) >= 10 ? RedisMapper.mapToCity(new ObjectMapper().readValue(jedis.get(String.valueOf(id)), CountryCity.class)) :
                    (cityDAO.findById(id).orElseThrow(RuntimeException::new));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (!countRequests.containsKey(id) && city != null) {
            countRequests.put(id, 1);
            logger.info(String.format("There is first request for city %s", city.getName()));
        } else {
            countRequests.replace(id, countRequests.get(id) + 1);

        }
        if (countRequests.get(id) == 10 && city != null) {
            var countryCity = RedisMapper.mapToCountryCity(List.of(city));
            jedis.set(String.valueOf(id), String.valueOf(countryCity));
            logger.info(String.format("City %s wrote to Redis", city.getName()));
        }
        return city;
    }

    public Map<Integer, Integer> getCountRequest() {
        return countRequests;
    }
}
