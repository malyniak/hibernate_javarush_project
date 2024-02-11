package org.project.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
        session.delete(k);
    }
    public K create(K k) {
        Session session = sessionFactory.openSession();
        session.persist(k);
        return k;
    }
    public K update (K k) {
        Session session = sessionFactory.openSession();
        session.merge(k);
        return k;
    }
}
