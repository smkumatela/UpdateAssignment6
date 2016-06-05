package com.example.songezo.assignment6_android.services;

import com.example.songezo.assignment6_android.domain.Log_Standings;

import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-08.
 */
public interface View_LogStandings_Services {

    Log_Standings findById(Long id);

    Log_Standings save(Log_Standings entity);

    Set<Log_Standings> findAll();
}
