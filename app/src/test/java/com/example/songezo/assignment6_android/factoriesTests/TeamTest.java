package com.example.songezo.assignment6_android.factoriesTests;

import com.example.songezo.assignment6_android.domain.Team;
import com.example.songezo.assignment6_android.factories.Team_Factory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Songezo on 2016-04-17.
 */
public class TeamTest {

    private Long id;
    //private Team team;
    //private Team_Factory team_factory;
    private Map<String, String> values;

    @Before
    public void setUp() throws Exception {
        values = new HashMap<>();
        //team = new Team();
        //team_factory = new Team_Factory();

        values.put("teamName", "Chiefs");
        values.put("nickName", "Amakhosi");
        values.put("location", "Naturena");
    }

    @Test
    public void testName() throws Exception {
        Team team = Team_Factory.createTeam(values, id);
        Assert.assertEquals("Chiefs", team.getTeamName());
    }

    @Test
    public void testUpdateTeam() throws Exception {
        Team team = Team_Factory.createTeam(values, id);
        Team newTeam = new Team.Builder()
                .copy(team)
                .teamName("pirates")
                .build();
        Assert.assertEquals("pirates", newTeam.getTeamName());

    }
}
