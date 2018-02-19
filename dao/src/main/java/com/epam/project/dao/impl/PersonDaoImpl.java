package com.epam.project.dao.impl;

import com.epam.project.dao.PersonDao;
import com.epam.project.dao.exception.EntityNotFoundException;
import com.epam.project.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<Person> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> persons = session.createQuery("from Person", Person.class).list();
        if (persons == null | persons.isEmpty()) {
            throw new EntityNotFoundException("Entity nor found");
        }
        return persons;

    }

    @Override
    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }

    @Override
    public Person findByMail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Person p WHERE p.email = :email");
        query.setParameter("email", email);
        List<Person> persons = query.getResultList();
        if (persons == null | persons.isEmpty()) {
            throw new EntityNotFoundException("Entity not found");
        }
        return persons.size() > 0 ? persons.get(0) : null;
    }
}
