package com.example.songezo.assignment6_android.services;

import com.example.songezo.assignment6_android.domain.Tournaments;

import java.util.Set;

/**
 * Created by Songezo on 2016-05-06.
 *
 * This service is for viewing all the details about the Tournaments
 */
public interface View_Tournaments_Service {

    Tournaments findById(Long id);

    Tournaments save(Tournaments entity);

    Set<Tournaments> findAll();

}
