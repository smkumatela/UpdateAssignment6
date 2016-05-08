package com.example.songezo.assignment6_android.services;

/**
 * Created by Songezo on 2016-05-06.
 *
 * This service is for viewing all the details about the Tournaments
 */
public interface View_Tournaments_Service {
    String viewTournaments(String name, int numOfTeams, double prize, Long id);

    Boolean isTournamentViewed();
    Boolean destroyTournament();
}
