package org.project.services;

import org.project.dao.CityDAO;
import org.project.entities.City;

import java.util.Optional;

public class CityService {
    private static final CityService instance=CityService.getInstance();
    private CityDAO cityDAO;
    private CityService(CityDAO cityDAO) {
        this.cityDAO=cityDAO;
    }
    public static CityService getInstance() {
        return instance;
    }
    public boolean delete(Integer id) {
        Optional<City> maybeCity = cityDAO.findById(id);
        if(maybeCity.isPresent()) {
            cityDAO.delete(maybeCity.get());
            return true;
        }
        return false;
    }
    public long getCitiesCount() {
       return cityDAO.getCitiesCount();
    }
    public City getById(Integer id) {
       return cityDAO.findById(id).orElseThrow(RuntimeException::new);
    }
}
