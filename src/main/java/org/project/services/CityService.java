package org.project.services;

import org.hibernate.SessionFactory;
import org.project.HibernateUtil;
import org.project.dao.CityDAO;
import org.project.entities.City;
import org.project.redis.RedisMapper;
import redis.clients.jedis.Jedis;

import java.util.*;

public class CityService {
    private SessionFactory sessionFactory = HibernateUtil.getMySqlSessionFactory();
    private CityDAO cityDAO;

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    private final Map<Integer, Integer> countRequests = new HashMap<>();

    public boolean delete(Integer id) {
        Optional<City> maybeCity = cityDAO.findById(id);
        if (maybeCity.isPresent()) {
            cityDAO.delete(maybeCity.get());
            return true;
        }
        return false;
    }

    public long getCitiesCount() {
        return cityDAO.getCitiesCount();
    }

    public City getById(Integer id) {
        var city = cityDAO.findById(id).orElseThrow(RuntimeException::new);
        if (!countRequests.containsKey(id)) {
            countRequests.put(id, 1);
        } else {
            countRequests.replace(id, countRequests.get(id) + 1);
            if (countRequests.get(id) >= 10) {
                var jedis = new Jedis("localhost", 6379);
                var countryCity = RedisMapper.map(List.of(city));
                jedis.set(String.valueOf(id), String.valueOf(countryCity));
            }
        }
        return city;
    }

    public Map<Integer, Integer> getCountRequest() {
        return countRequests;
    }
}
