package com.epam.project.service.impl;

import com.epam.project.dao.PersonDao;
import com.epam.project.entity.Person;
import com.epam.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ,
            rollbackFor = Exception.class)
    public void update(Person person) {
        Person person1 = personDao.findByMail(person.getEmail());
        person1.setRole(person.getRole());
        personDao.update(person1);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByMail(String eMail) {
        return personDao.findByMail(eMail);
    }
}
