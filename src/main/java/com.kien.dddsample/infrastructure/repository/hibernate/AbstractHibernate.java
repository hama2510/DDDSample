/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kien.dddsample.infrastructure.repository.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHibernate<E> {

    @Autowired
    private SessionFactory sessionFactory;
    private final Class<E> clazz;

    public AbstractHibernate(Class<E> clazz) {
        this.clazz = clazz;
    }

    protected Session getSession() {
        Session session;
//        try {
        session = sessionFactory.getCurrentSession();
//        } catch (HibernateException e) {
//            session = sessionFactory.openSession();
//        }
        return session;
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return sessionFactory.getCriteriaBuilder();
    }

    public List<E> findAll() {
        CriteriaQuery query = getSession().getCriteriaBuilder().createQuery(clazz);
        Root<E> root = query.from(clazz);
        query.select(root);
        return getSession().createQuery(query).list();
    }

    protected List<E> query(AbstractQuery abstractQuery) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(clazz);
        Root<E> root = query.from(clazz);
        query.select(root).where(abstractQuery.createExpression(builder, root));
        return getSession().createQuery(query).list();
    }

    public void insert(E e) {
        getSession().save(e);
    }

    public void delete(E e) {
        getSession().delete(e);
    }

    public void update(E e) {
        getSession().merge(e);
    }

    public E get(String id) {
        return getSession().load(clazz, id);
    }
}
