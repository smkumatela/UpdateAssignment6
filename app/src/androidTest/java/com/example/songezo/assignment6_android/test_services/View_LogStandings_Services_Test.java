package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.services.Impl.View_LogStandings_Service_Impl;

import junit.framework.Assert;

import java.util.Map;

/**
 * Created by Songezo on 2016-05-08.
 */
public class View_LogStandings_Services_Test extends AndroidTestCase {
    private View_LogStandings_Service_Impl logStandingsService;
    private Boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_LogStandings_Service_Impl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
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

    public void testViewLog(Map<String, Integer> values, Long id) throws Exception {
        String viewLog = logStandingsService.viewLog(values, id);
        Assert.assertEquals("VIEWED", viewLog);
    }

    public void testIsLogViewed() throws Exception {
        Boolean isViewLod = logStandingsService.isLogViewed();
        Assert.assertEquals("VIEWED", isViewLod);
    }

    public void testDestroyLog() throws Exception {
        Boolean destroyLog = logStandingsService.destroyLog();
        Assert.assertEquals("DESTROYED", destroyLog);
    }
}
