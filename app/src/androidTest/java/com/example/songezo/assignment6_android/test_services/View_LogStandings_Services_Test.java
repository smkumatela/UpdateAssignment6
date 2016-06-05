package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Log_Standings;
import com.example.songezo.assignment6_android.factories.Log_Standings_Factory;
import com.example.songezo.assignment6_android.services.Impl.View_LogStandings_Service_Impl;

import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-08.
 */
public class View_LogStandings_Services_Test extends AndroidTestCase {
    private View_LogStandings_Service_Impl logStandingsService;
    private Boolean isBound;
    private static final String TAG = "LOG STANDINGS TEST";

    Map<String, Integer> values;
    Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_LogStandings_Service_Impl.class);
        GlobalContext.context = this.getContext();
        logStandingsService = View_LogStandings_Service_Impl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        values = new HashMap<>();
    }

    public ServiceConnection connection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_LogStandings_Service_Impl.View_LogStandings_Service_LocalBinder binder
                    = (View_LogStandings_Service_Impl.View_LogStandings_Service_LocalBinder) service;
            logStandingsService = binder.getLogStandings();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntity() throws Exception {
        values.put("Played", 8);
        values.put("Won", 5);
        values.put("Drawn", 2);
        values.put("Lost", 1);
        values.put("Points", 17);
        id = 12355L;
        Log_Standings logEntity = Log_Standings_Factory.createLogStandings( values, id);
        Log_Standings newLogEntity = logStandingsService.save(logEntity);
        Assert.assertNotNull(newLogEntity);
    }

    public void testCreateAndFindListOfEntities() throws Exception {
        values.put("Played", 8);
        values.put("Won", 4);
        values.put("Drawn", 3);
        values.put("Lost", 1);
        values.put("Points", 15);
        id = 12365L;
        Log_Standings logStanding1 = Log_Standings_Factory.createLogStandings( values, id);
        logStandingsService.save(logStanding1);

        values.put("Played", 8);
        values.put("Won", 7);
        values.put("Drawn", 0);
        values.put("Lost", 1);
        values.put("Points", 21);
        id = 12375L;
        Log_Standings logStanding2 = Log_Standings_Factory.createLogStandings( values, id);
        logStandingsService.save(logStanding2);

        Set<Log_Standings> logStandingsSet = logStandingsService.findAll();
        Assert.assertTrue(logStandingsSet.size() > 1);
    }
}
