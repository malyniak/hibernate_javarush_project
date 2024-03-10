package org.project.services;


import org.hibernate.SessionFactory;
import org.project.*;
import org.project.dao.CityDAO;
import org.project.entities.City;
import org.project.exceptions.CityNotFoundException;
import org.project.redis.RedisMapper;
import org.slf4j.*;
import redis.clients.jedis.Jedis;
import java.util.*;

public class CityService {
    private static final CityService INSTANCE = new CityService();
    private final CityDAO cityDAO = CityDAO.getInstance();
    private final Logger logger = LoggerFactory.getLogger(City.class);
    private final SessionFactory sessionFactory = HibernateUtil.getMySqlSessionFactory();
    private final Jedis jedis;

    private CityService() {
        jedis = new Jedis("localhost", 6379);
    }

    public static CityService getInstance() {
        return INSTANCE;
    }

    private final Map<Integer, Integer> countRequests = new HashMap<>();

    public List<City> getAll() {
        return cityDAO.findAll();
    }

    public void create(City city) {
        cityDAO.create(city);
    }

    public City update(City city) {
        return cityDAO.update(city);
    }


    public boolean remove(City city) {
        Optional<City> maybeCity = cityDAO.findById(city.getId());
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
        int cashingValue = 10;
        City city = (cityDAO.findById(id).orElseThrow(() ->
                new CityNotFoundException(String.format("City with id %d not found", id))));
        if (!countRequests.containsKey(id)) {
            countRequests.put(id, 1);
            logger.info(String.format("There is first request for city %s", city.getName()));
        } else if (countRequests.get(id) < cashingValue) {
            countRequests.replace(id, countRequests.get(id) + 1);
        } else {
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
