package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Tournaments;
import com.example.songezo.assignment6_android.factories.Tournament_Factory;
import com.example.songezo.assignment6_android.services.Impl.View_Tournament_Service_Impl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Songezo on 2016-05-07.
 */
public class View_Tournaments_Test extends AndroidTestCase {

    private View_Tournament_Service_Impl view_tournament_service;
    private Boolean isBound;
    private static final String TAG = "TOURNAMENT TEST";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_Tournament_Service_Impl.class);
        GlobalContext.context = this.getContext();
        view_tournament_service = View_Tournament_Service_Impl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_Tournament_Service_Impl.View_Tournament_Service_localBinder binder =
                    (View_Tournament_Service_Impl.View_Tournament_Service_localBinder) service;
            view_tournament_service = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };



    public void testCreateEntities() throws Exception {
        Tournaments tournament = Tournament_Factory
                .createTournament("Nedbank Cup", 32, 10.000000, 12310L);
        Tournaments newTournament =  view_tournament_service.save(tournament);
        Assert.assertNotNull(newTournament);
    }

    public void testCreateAndFindListOfEntities() throws Exception {
        Tournaments createTournament1 = Tournament_Factory
                .createTournament("MTN TOP 8", 8, 8.000000, 12320L);
        Tournaments createTournament2 = Tournament_Factory
                .createTournament("Carling Black Label", 2, 20.000000, 12330L);
        Tournaments createTournament3 = Tournament_Factory
                .createTournament("Afcon", 16,15.000000, 12340L);

        view_tournament_service.save(createTournament1);
        view_tournament_service.save(createTournament2);
        view_tournament_service.save(createTournament3);

        Set<Tournaments> tournamentsSet = view_tournament_service.findAll();
        Assert.assertTrue(tournamentsSet.size() > 2);
    }

}
