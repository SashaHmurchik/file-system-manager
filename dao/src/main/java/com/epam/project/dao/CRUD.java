package com.epam.project.dao;

import java.util.List;

interface CRUD<K, E> {

        List<E> findAll();

        void update(E e);

}
