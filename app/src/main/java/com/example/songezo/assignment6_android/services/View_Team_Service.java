package com.example.songezo.assignment6_android.services;

import com.example.songezo.assignment6_android.domain.Team;

import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-07.
 *
 * This service is for viewing all the details about the Teams
 */
public interface View_Team_Service {

    Team findById(Long id);

    Team save(Team entity);

    Set<Team> findAll();
}
