package org.project.redis;

import org.project.entities.City;
import org.project.entities.Country;
import org.project.entities.CountryLanguage;

import java.util.*;
import java.util.stream.Collectors;

public class RedisMapper {
    public static List<CountryCity> mapToCountryCity(List<City> list) {
        List<CountryCity> countryCityList = list.stream().map(city -> {
            CountryCity countryCity = new CountryCity();
            countryCity.setId(city.getId());
            countryCity.setName(city.getName());
            countryCity.setDistrict(city.getDistrict());
            countryCity.setPopulation(city.getPopulation());
            Country country = city.getCountry();
            countryCity.setCode(country.getCode());
            countryCity.setAlternativeCode(country.getCode2());
            countryCity.setCountryName(country.getName());
            countryCity.setContinent(country.getContinent());
            countryCity.setRegion(country.getRegion());
            countryCity.setSurfaceArea(country.getSurfaceArea());
            countryCity.setCountry_Population(country.getPopulation());

            var countryLanguages = country.getCountryLanguage();
            Set<Language> languages = countryLanguages.stream().map(countryLanguage -> {
                Language language = new Language();
                language.setLanguage(countryLanguage.getLanguage());
                language.setOfficial(countryLanguage.getIsOfficial());
                language.setPercentage(countryLanguage.getPercentage());
                return language;
            }).collect(Collectors.toSet());
            countryCity.setLanguages(languages);
            return countryCity;
        }).collect(Collectors.toList());
        return countryCityList;
    }
    public static City mapToCity(CountryCity countryCity) {
        City city= new City();
        city.setName(countryCity.getName());
        city.setDistrict(countryCity.getDistrict());
        city.setPopulation(countryCity.getPopulation());
        Country country=new Country();
        country.setName(countryCity.getCountryName());
        country.setId(countryCity.getId());
        country.setCode(countryCity.getCode());
        country.setCode2(countryCity.getAlternativeCode());
        country.setPopulation(countryCity.getCountry_Population());
        country.setRegion(countryCity.getRegion());
        country.setContinent(countryCity.getContinent());
        var languages = countryCity.getLanguages();
        Set<CountryLanguage> countryLanguages=new HashSet<>();
        for(Language language : languages) {
            var countryLanguage = new CountryLanguage(country, language.getLanguage(), language.getOfficial(), language.getPercentage());
            countryLanguages.add(countryLanguage);
        }
        country.setCountryLanguage(countryLanguages);
        country.setSurfaceArea(countryCity.getSurfaceArea());
        city.setCountry(country);
        return city;
    }
}
