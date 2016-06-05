package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.PremierSoccerLeague;
import com.example.songezo.assignment6_android.factories.PremierSoccerLeague_Factory;
import com.example.songezo.assignment6_android.services.Impl.Add_PremierSoccerLeague_Service_Impl;

import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-06.
 */
public class Add_PremierSoccerLeague_Service_Test extends AndroidTestCase {
    private Add_PremierSoccerLeague_Service_Impl premierSoccerLeagueService;
    private Boolean isBound;
    private static final String TAG = "PREMIER SOCCER LEAGUE TEST";

    Map<String, String> values;
    long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), Add_PremierSoccerLeague_Service_Impl.class);
        GlobalContext.context = this.getContext();
        premierSoccerLeagueService = Add_PremierSoccerLeague_Service_Impl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        values = new HashMap<>();
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Add_PremierSoccerLeague_Service_Impl.AddPremierSoccerLeagueLocalBinder binder
                    = (Add_PremierSoccerLeague_Service_Impl.AddPremierSoccerLeagueLocalBinder) service;
            premierSoccerLeagueService = binder.getPremierSoccerLeague();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntity() throws Exception {
        values.put("LeagueName", "English Premier League");
        values.put("abbreviation", "EPL");
        values.put("stadium", "Old Trafford");
        id = 12385L;
        PremierSoccerLeague pslEntity = PremierSoccerLeague_Factory
                .createPremierSoccerLeague(values, id);
        PremierSoccerLeague newPslEntity = premierSoccerLeagueService.save(pslEntity);
        Assert.assertNotNull(newPslEntity);
    }

    public void testCreateAndFindListOfEntities() throws Exception {
        values.put("LeagueName", "Absa Premiership");
        values.put("abbreviation", "PSL");
        values.put("stadium", "Soccer City");
        id = 12395L;
        PremierSoccerLeague premierSoccerLeague1 = PremierSoccerLeague_Factory
                .createPremierSoccerLeague(values, id);
        premierSoccerLeagueService.save(premierSoccerLeague1);

        values.put("LeagueName", "La Liga");
        values.put("abbreviation", "Liga");
        values.put("stadium", "San Siro");
        id = 12319L;
        PremierSoccerLeague premierSoccerLeague2 = PremierSoccerLeague_Factory
                .createPremierSoccerLeague(values, id);
        premierSoccerLeagueService.save(premierSoccerLeague2);

        Set<PremierSoccerLeague> premierSoccerLeagueSet = premierSoccerLeagueService.findAll();
        Assert.assertTrue(premierSoccerLeagueSet.size() > 1);
    }
}
