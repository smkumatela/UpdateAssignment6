package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.services.Impl.View_Sponsor_Service_Impl;

import junit.framework.Assert;

import java.util.Map;

/**
 * Created by Songezo on 2016-05-07.
 */
public class View_Sponsor_Test extends AndroidTestCase {
    private View_Sponsor_Service_Impl sponsorService;
    private Boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_Sponsor_Service_Impl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_Sponsor_Service_Impl.View_Sponsor_Service_LocalBinder binder
                    = (View_Sponsor_Service_Impl.View_Sponsor_Service_LocalBinder) service;
            sponsorService = binder.getSponsor();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testViewSponsor(Map<String, String> values, Long id) throws Exception {
        String viewSponsor = sponsorService.viewSponsor(values, id);
        Assert.assertEquals("VIEWED", viewSponsor);
    }

    public void testIsSponsorViewed() throws Exception {
        Boolean viewing = sponsorService.isSponsorViewed();
        Assert.assertEquals("VIEWED", viewing);
    }

    public void testdetroySponsor() throws Exception {
        Boolean destroy = sponsorService.destroySponsor();
        Assert.assertEquals("DESTROYED", destroy);
    }
}
