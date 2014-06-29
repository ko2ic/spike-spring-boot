package com.github.ko2ic.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.github.ko2ic.core.Person;
import com.google.common.base.Optional;

@Repository
public class PeopleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Person> findById(Long id) {
        return Optional.fromNullable(entityManager.find(Person.class, id));
    }

    public Person create(Person person) {
        entityManager.persist(person);
        return person;
    }

    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        return entityManager.createNamedQuery("com.github.ko2ic.core.People.findAll").getResultList();
    }
}