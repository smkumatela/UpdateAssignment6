package com.example.songezo.assignment6_android.services;

import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.domain.Team;

import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-08.
 *
 * This service is for viewing all the details about the Stadiums
 */
public interface View_Stadiums_Service {

    Stadium findById(Long id);

    Stadium save(Stadium entity);

    Set<Stadium> findAll();

}
