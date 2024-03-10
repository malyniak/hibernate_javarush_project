package org.project.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.project.entities.City;
import org.project.redis.CountryCity;
import org.project.redis.RedisMapper;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class RedisService {
    private static final RedisService INSTANCE = new RedisService();
    private RedisService() {

    }
    public static RedisService getInstance() {
        return INSTANCE;
    }
    public void pushDataToRedis(List<City> cities, ObjectMapper objectMapper, Jedis jedis) {
        var countryCityList = RedisMapper.mapToCountryCity(cities);
        for (CountryCity countryCity : countryCityList) {
            try {
                jedis.set(String.valueOf(countryCity.getId()), objectMapper.writeValueAsString(countryCity));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public  List<CountryCity> getDataFromRedis(ObjectMapper objectMapper, Jedis jedis) {
        List<CountryCity> countryCityList = new ArrayList<>();
        var keys = jedis.keys("*");
        for (String id : keys) {
            try {
                countryCityList.add(objectMapper.readValue(jedis.get(id), CountryCity.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return countryCityList;
    }
}
