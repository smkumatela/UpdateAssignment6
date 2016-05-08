package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.services.Impl.View_Team_Service_Impl;

import junit.framework.Assert;

import java.util.Map;

/**
 * Created by Songezo on 2016-05-07.
 */
public class View_Team_Service_Test extends AndroidTestCase {
    private View_Team_Service_Impl teamService;
    private Boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_Team_Service_Impl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection connection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_Team_Service_Impl.View_Team_Service_LocalBinder binder
                    = (View_Team_Service_Impl.View_Team_Service_LocalBinder) service;
            teamService = binder.getTeam();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

//    public void testIsTeamViewed() throws Exception {
//        String viewTeam = teamService.viewTeam()
//    }


    public void testIsTeamViewed() throws Exception {
       Boolean teamViewed = teamService.isTeamViewed();
        Assert.assertEquals("VIEWED", teamViewed);
    }

    public void testDestroyTeam() throws Exception {
        Boolean destroyed = teamService.destroyTeam();
        Assert.assertEquals("DESTROYED", destroyed);
    }
}

