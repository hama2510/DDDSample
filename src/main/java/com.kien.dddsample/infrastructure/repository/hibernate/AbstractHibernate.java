/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kien.dddsample.infrastructure.repository.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        session = sessionFactory.getCurrentSession();
        return session;
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return sessionFactory.getCriteriaBuilder();
    }

    public ArrayList<E> findAll() {
        Transaction trans = getSession().beginTransaction();
        CriteriaQuery query = getSession().getCriteriaBuilder().createQuery(clazz);
        Root<E> root = query.from(clazz);
        query.select(root);
        ArrayList<E> arr = (ArrayList<E>) getSession().createQuery(query).list();
        trans.commit();
        return arr;
    }

    protected ArrayList<E> query(AbstractQuery abstractQuery) {
        Transaction trans = getSession().beginTransaction();
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(clazz);
        Root<E> root = query.from(clazz);
        query.select(root).where(abstractQuery.createExpression(builder, root));
        ArrayList<E> arr = (ArrayList<E>) getSession().createQuery(query).list();
        trans.commit();
        return arr;
    }

    public E get(String id) {
        Transaction trans = getSession().beginTransaction();
        E e = getSession().load(clazz, id);
        trans.commit();
        return e;
    }

    public void save(E e) {
        Transaction trans = getSession().beginTransaction();
        getSession().saveOrUpdate(e);
        trans.commit();
    }

    public void delete(E e) {
        Session session = getSession();
        Transaction trans = session.beginTransaction();
        session.delete(e);
        trans.commit();
    }
}
