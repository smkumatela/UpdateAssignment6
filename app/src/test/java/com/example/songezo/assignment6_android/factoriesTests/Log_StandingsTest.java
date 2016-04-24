package com.example.songezo.assignment6_android.factoriesTests;

import junit.framework.Assert;
///import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.songezo.assignment6_android.domain.Log_Standings;
import com.example.songezo.assignment6_android.factories.Log_Standings_Factory;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Songezo on 2016-04-07.
 */
public class Log_StandingsTest {

    private Map<String, Integer> values;
    private Log_Standings_Factory log;

    @Before
    public void setUp() throws Exception {
        values = new HashMap<String, Integer>();
        log = new Log_Standings_Factory();

        values.put("Played", 4);
        values.put("Won", 3);
        values.put("Lost", 1);
        values.put("Drawn", 0);
        values.put("Points", 9);
    }

    @Test
    public void testCreate_Log_Standings() throws Exception {
        Log_Standings logStandings = Log_Standings_Factory.createLogStandings(values);

        junit.framework.Assert.assertEquals(3, logStandings.getGamesWon());
        junit.framework.Assert.assertEquals(9, logStandings.getPoints());
        System.out.println("is it the one: " + " " + logStandings.getGamesDrawn());
    }

    @Test
    public void testUpdate_Log_Standings() throws Exception {

        Log_Standings logStandings = Log_Standings_Factory.createLogStandings(values);
        Log_Standings table = new Log_Standings
                .Builder()
                .teamName("Chiefs").copy(logStandings).gamesDrawn(2)
                .gamesLost(1).gamesPlayed(5).gamesWon(2)
                .points(5).build();

        junit.framework.Assert.assertEquals(2, table.getGamesWon());
        junit.framework.Assert.assertEquals(4, logStandings.getGamesPlayed());
        System.out.println("is it the one: " + " " + table.getGamesDrawn() + "\n");
        System.out.println("is it the one: " + " "+ logStandings.getGamesDrawn());

    }
}
