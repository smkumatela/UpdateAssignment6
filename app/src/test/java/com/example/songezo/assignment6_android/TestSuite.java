package com.example.songezo.assignment6_android;

import com.example.songezo.assignment6_android.factoriesTests.BroadcasterTest;
import com.example.songezo.assignment6_android.factoriesTests.Log_StandingsTest;
import com.example.songezo.assignment6_android.factoriesTests.PlayerTest;
import com.example.songezo.assignment6_android.factoriesTests.PremierSoccerLeagueTest;
import com.example.songezo.assignment6_android.factoriesTests.SponsorTest;
import com.example.songezo.assignment6_android.factoriesTests.StadiumTest;
import com.example.songezo.assignment6_android.factoriesTests.TeamTest;
import com.example.songezo.assignment6_android.factoriesTests.TournamentTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Songezo on 2016-04-17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ BroadcasterTest.class,
        Log_StandingsTest.class,
        PlayerTest.class,
        PremierSoccerLeagueTest.class,
        StadiumTest.class,
        TournamentTest.class,
        SponsorTest.class,
        TeamTest.class

})

public class TestSuite {
}
