package com.example.songezo.assignment6_android.services;

import com.example.songezo.assignment6_android.domain.PremierSoccerLeague;

import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-06.
 *
 *  This service if for creating or adding a premier soccer league to the App, it is
 *  a bound service
 */
public interface Add_PremierSoccerLeague_Service {

    PremierSoccerLeague findById(Long id);

    PremierSoccerLeague save(PremierSoccerLeague entity);

    Set<PremierSoccerLeague> findAll();
}
