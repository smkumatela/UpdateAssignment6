package com.example.songezo.assignment6_android.factories;

import com.example.songezo.assignment6_android.domain.Team;

import java.util.Map;

/**
 * Created by Songezo on 2016-04-14.
 */
public class Team_Factory {

    private Long id;
    private String teamName;
    private String teamNickName;
    private String teamLocation;

    Team team;

    private static Team_Factory teams = null;

    public Team_Factory() {
    }

    public static Team_Factory getTeamsInstance(){

        if (teams == null){
            teams = new Team_Factory();
        }
        return teams;
    }

    public static Team createTeam(Map<String, String> values, Long id){
        Team team1 = new Team.Builder()
                .teamName(values.get("teamName"))
                .teamNickName(values.get("nickName"))
                .teamLocation(values.get("location"))
                .id(12369L)
                .build();
        return team1;
    }
}
