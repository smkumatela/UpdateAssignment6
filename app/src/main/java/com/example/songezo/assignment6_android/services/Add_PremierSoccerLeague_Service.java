package com.example.songezo.assignment6_android.services;

import java.util.Map;
/**
 * Created by Songezo on 2016-05-06.
 *
 *  This service if for creating or adding a premier soccer league to the App, it is
 *  a bound service
 */
public interface Add_PremierSoccerLeague_Service {

    String create_league(Map<String, String> values);

    boolean isLeagueCreated();

    boolean destroyLeague();
}
