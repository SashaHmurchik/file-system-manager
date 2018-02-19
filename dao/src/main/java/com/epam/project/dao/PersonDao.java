package com.epam.project.dao;

import com.epam.project.entity.Person;

public interface PersonDao extends CRUD<Long, Person> {

    Person findByMail(String email);

}
