package org.project.dao;

import org.hibernate.SessionFactory;
import org.project.HibernateUtil;
import org.project.entities.CountryLanguage;

public class CountryLanguageDAO extends AbstractDAO<CountryLanguage, Integer> {
    private static final CountryLanguageDAO INSTANCE = new CountryLanguageDAO(HibernateUtil.getMySqlSessionFactory());
    private final SessionFactory sessionFactory;
    private CountryLanguageDAO(SessionFactory sessionFactory) {
        super(CountryLanguage.class);
        this.sessionFactory=sessionFactory;
    }
    public static CountryLanguageDAO getInstance() {
        return INSTANCE;
    }
}
