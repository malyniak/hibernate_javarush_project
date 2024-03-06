package org.project.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.*;
import org.project.HibernateUtil;
import java.io.Serializable;
import java.util.*;

public abstract class  AbstractDAO<K, I extends Serializable> {
    private SessionFactory sessionFactory= HibernateUtil.getMySqlSessionFactory();
    private Class<K> clazz;

    public AbstractDAO(Class<K> clazz) {
        this.clazz = clazz;
    }

    public Optional<K> findById(I id) {
        Session session = sessionFactory.openSession();
        return Optional.ofNullable(session.get(clazz, id));
    }
    public List<K> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<K> query = criteriaBuilder.createQuery(clazz);
        query.from(clazz);
        return session.createQuery(query).getResultList();
    }
    public void delete (K k) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(k);
        transaction.commit();
    }
    public K create(K k) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(k);
        transaction.commit();
        return k;
    }
    public K update (K k) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(k);
        transaction.commit();
        return k;
    }
}
