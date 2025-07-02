package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MyDao<T> {
    private EntityManager em;

    public MyDao(EntityManager em) {
        this.em = em;
    }

    public void save(T tipoObj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tipoObj);
        transaction.commit();
    }
}
