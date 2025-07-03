package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class MyDao<T> {
    private final EntityManager em;

    public MyDao(EntityManager em) {
        this.em = em;
    }


    public T getById(Class<T> entityClass, String id) {
        UUID actual_id = UUID.fromString(id);
        T objFromDb = em.find(entityClass, actual_id);
        if (objFromDb == null) {
            throw new EntityNotFoundException("Oggetto non trovato");
        }
        return objFromDb;
    }


    public void save(T tipoObj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tipoObj);
        transaction.commit();
    }


}
