package org.project.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Entity
@Table(name = "country_language", schema = "world")
public class CountryLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(name = "is_official", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Byte isOfficial;
    @Column(precision = 4, scale = 1)
    private BigDecimal percentage;

    public CountryLanguage() {
    }

    public CountryLanguage(Integer id, Country country, Byte isOfficial, BigDecimal percentage) {
        this.id = id;
        this.country = country;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Byte getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(Byte isOfficial) {
        this.isOfficial = isOfficial;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
