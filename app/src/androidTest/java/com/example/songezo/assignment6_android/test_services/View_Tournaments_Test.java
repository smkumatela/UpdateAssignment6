package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.domain.Tournaments;
import com.example.songezo.assignment6_android.services.Impl.View_Tournament_Service_Impl;

import junit.framework.Assert;

/**
 * Created by Songezo on 2016-05-07.
 */
public class View_Tournaments_Test extends AndroidTestCase {
    private View_Tournament_Service_Impl view_tournament_service;
    private Boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_Tournament_Service_Impl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_Tournament_Service_Impl.View_Tournaments_Service_LocalBinder binder
                    = (View_Tournament_Service_Impl.View_Tournaments_Service_LocalBinder) service;
            view_tournament_service = binder.getTournament();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };


    public void testViewTournaments() throws Exception {
        String viewTournaments = view_tournament_service
                .viewTournaments("Nedbank Cup", 32, 10.000000, 22376L);
        Assert.assertEquals("ACTIVATED", viewTournaments);
    }

    public void testIsTournamentViewed() throws Exception {
        Boolean viewed = view_tournament_service.isTournamentViewed();
        Assert.assertEquals("ACTIVATED", viewed);
    }

    public void testDestroyTournament() throws Exception {
        Boolean destroyed = view_tournament_service.destroyTournament();
        Assert.assertEquals("ACTIVATED", destroyed);
    }
}
