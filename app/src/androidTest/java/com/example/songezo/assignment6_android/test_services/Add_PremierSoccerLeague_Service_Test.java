package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.services.Impl.Add_PremierSoccerLeague_Service_Impl;

import junit.framework.Assert;

/**
 * Created by Songezo on 2016-05-06.
 */
public class Add_PremierSoccerLeague_Service_Test extends AndroidTestCase {
    private Add_PremierSoccerLeague_Service_Impl premierSoccerLeagueService;
    private Boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), Add_PremierSoccerLeague_Service_Impl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
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

//    public void testAddPremierSoccerLeague() throws Exception {
//        String addPSL = premierSoccerLeagueService.create_league()
//    }


    public void testIsLeagueCreated() throws Exception {
        Boolean created = premierSoccerLeagueService.isLeagueCreated();
        Assert.assertEquals("CREATED", created);
    }

    public void testIsDestroyed() throws Exception {
        Boolean destroyed = premierSoccerLeagueService.destroyLeague();
        Assert.assertEquals("DESTROYED", destroyed);
    }
}
