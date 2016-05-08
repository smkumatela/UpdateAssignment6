package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.repository.Stadium_Repository;
import com.example.songezo.assignment6_android.services.Impl.View_Stadium_Service_Impl;

import junit.framework.Assert;

import java.util.Map;

/**
 * Created by Songezo on 2016-05-08.
 */
public class View_Stadiums_Service_Test extends AndroidTestCase {
    private View_Stadium_Service_Impl stadiumService;
    private Boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_Stadium_Service_Impl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_Stadium_Service_Impl.View_Stadium_Service_LocalBinder binder
                    = (View_Stadium_Service_Impl.View_Stadium_Service_LocalBinder) service;
            stadiumService = binder.getStadium();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testViewStadium(Map<String, String> values, Long id) throws Exception {
        String viewStadium = stadiumService.viewStadium(values, id);
        Assert.assertEquals("VIEWED", viewStadium);
    }


    public void testIsStadiumViewed() throws Exception {
        Boolean isViewStadium = stadiumService.isStadiumViewed();
        Assert.assertEquals("VIEWED", isViewStadium);
    }

    public void testDestroyStadium() throws Exception {
        Boolean destroyed = stadiumService.destroyStadium();
        Assert.assertEquals("DESTROYED", destroyed);
    }
}
