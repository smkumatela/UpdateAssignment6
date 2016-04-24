package com.example.songezo.assignment6_android.repository;

import java.util.Set;

/**
 * Created by Songezo on 2016-04-20.
 */
public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
