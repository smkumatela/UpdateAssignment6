package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Team;
import com.example.songezo.assignment6_android.factories.Team_Factory;
import com.example.songezo.assignment6_android.services.Impl.View_Team_Service_Impl;

import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-07.
 */
public class View_Team_Service_Test extends AndroidTestCase {
    private View_Team_Service_Impl viewTeamService;
    private Boolean isBound;
    private static final String TAG = "TEAM TEST";

    Map<String, String> values;
    Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_Team_Service_Impl.class);
        GlobalContext.context = this.getContext();
        viewTeamService = View_Team_Service_Impl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        values = new HashMap<>();
    }

    public ServiceConnection connection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_Team_Service_Impl.View_Team_Service_LocalBinder binder
                    = (View_Team_Service_Impl.View_Team_Service_LocalBinder) service;
            viewTeamService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception {
        values.put("teamName", "Kaizer Chiefs");
        values.put("nickName", "Amakhosi");
        values.put("location", "Naturena");
        id = 12350L;
        Team teamEntity = Team_Factory.createTeam(values, id);
        Team newTeam = viewTeamService.save(teamEntity);
        Assert.assertNotNull(newTeam);
    }

    public void testCreateAndFindListOfEntities() throws Exception {
        values.put("teamName", "Orlando Pirates");
        values.put("nickName", "Bhakagugu");
        values.put("location", "Soweto");
        id = 12360L;
        Team createTeam1 = Team_Factory.createTeam(values, id);
        viewTeamService.save(createTeam1);

        values.put("teamName", "Sundowns");
        values.put("nickName", "Brazilians");
        values.put("location", "Pretoria");
        id = 12370L;
        Team createTeam2 = Team_Factory.createTeam(values, id);
        viewTeamService.save(createTeam2);

        Set<Team> teamSet = viewTeamService.findAll();
        Assert.assertTrue(teamSet.size() > 1);

    }
}

