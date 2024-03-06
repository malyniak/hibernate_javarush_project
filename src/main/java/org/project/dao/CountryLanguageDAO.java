package org.project.dao;

import org.hibernate.SessionFactory;
import org.project.entities.CountryLanguage;

public class CountryLanguageDAO extends AbstractDAO<CountryLanguage, Integer> {
    private final SessionFactory sessionFactory;
    public CountryLanguageDAO(SessionFactory sessionFactory) {
        super(CountryLanguage.class);
        this.sessionFactory=sessionFactory;
    }

}
