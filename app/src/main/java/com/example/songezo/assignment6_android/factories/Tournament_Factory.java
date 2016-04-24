package com.example.songezo.assignment6_android.factories;

import com.example.songezo.assignment6_android.domain.Tournaments;


/**
 * Created by Songezo on 2016-04-14.
 */
public class Tournament_Factory {

    private Long id;
//    private String tName;
//    private int numOfTeams;
//    private double prizeMoney;
//
//    Tournaments tournaments;

    //private static TechnicalTeam_Factory technicalTeam = null;
    private static Tournament_Factory tournament = null;

    public Tournament_Factory() {
    }

    public static Tournament_Factory getTechnicalTeamInstance(){
        if (tournament == null){
            tournament = new Tournament_Factory();
        }
        return tournament;
    }

    public static Tournaments createTournament(String name, int numOfTeams, double prize, Long id){
        Tournaments tournament = new Tournaments.Builder()
                .tName(name)
                .numOfTeams(numOfTeams)
                .prizeMoney(prize)
                .build();
        return tournament;
    }
}
