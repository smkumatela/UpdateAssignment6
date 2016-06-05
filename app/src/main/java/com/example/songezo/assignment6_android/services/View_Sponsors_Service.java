package com.example.songezo.assignment6_android.services;

import com.example.songezo.assignment6_android.domain.Sponsor;

import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-07.
 *
 * This service is for viewing all the details about the Sponsors
 */
public interface View_Sponsors_Service {

    Sponsor findById(Long id);

    Sponsor save(Sponsor entity);

    Set<Sponsor> findAll();
}
