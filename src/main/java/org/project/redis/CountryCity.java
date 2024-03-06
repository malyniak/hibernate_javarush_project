package org.project.redis;

import org.project.entities.Continent;
import java.math.BigDecimal;
import java.util.Set;

public class CountryCity {
    private Integer id;
    private String name;
    private String district;
    private Integer population;
    private String code;
    private String alternativeCode;
    private String countryName;
    private Continent continent;
    private String region;
    private BigDecimal surfaceArea;
    private Integer country_Population;
    private Set<Language> languages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlternativeCode() {
        return alternativeCode;
    }

    public void setAlternativeCode(String alternativeCode) {
        this.alternativeCode = alternativeCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Integer getCountry_Population() {
        return country_Population;
    }

    public void setCountry_Population(Integer country_Population) {
        this.country_Population = country_Population;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "CountryCity{" +
                "name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", code='" + code + '\'' +
                ", alternativeCode='" + alternativeCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", continent=" + continent +
                ", region='" + region + '\'' +
                ", surfaceArea=" + surfaceArea +
                ", country_Population=" + country_Population +
                ", languages=" + languages +
                '}';
    }
}
