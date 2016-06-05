package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Sponsor;
import com.example.songezo.assignment6_android.factories.Sponsor_Factory;
import com.example.songezo.assignment6_android.services.Impl.View_Sponsor_Service_Impl;

import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-07.
 */
public class View_Sponsor_Test extends AndroidTestCase {
    private View_Sponsor_Service_Impl sponsorService;
    private Boolean isBound;
    private static final String TAG = "SPONSOR TEST";

    Map<String, String> values;
    Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_Sponsor_Service_Impl.class);
        GlobalContext.context = this.getContext();
        sponsorService = View_Sponsor_Service_Impl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        values = new HashMap<>();
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_Sponsor_Service_Impl.View_Sponsor_Service_LocalBinder binder
                    = (View_Sponsor_Service_Impl.View_Sponsor_Service_LocalBinder) service;
            sponsorService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntity() throws Exception {
        values.put("name", "Nike");
        values.put("sponsors", "Ronaldo");
        id = 12325L;
        Sponsor sponsorEntity = Sponsor_Factory.createSponsor(values, id);
        Sponsor newSponsor = sponsorService.save(sponsorEntity);
        Assert.assertNotNull(newSponsor);
    }

    public void testCreateAndFindListOfEntities() throws Exception {
        values.put("name", "Pumma");
        values.put("sponsors", "Parker");
        id = 12335L;
        Sponsor sponsor1 = Sponsor_Factory.createSponsor(values, id);
        sponsorService.save(sponsor1);

        values.put("name", "ADIDAS");
        values.put("sponsors", "Isa Sar");
        id = 12345L;
        Sponsor sponsor2 = Sponsor_Factory.createSponsor(values, id);
        sponsorService.save(sponsor2);

        Set<Sponsor> sponsorSet = sponsorService.findAll();
        Assert.assertTrue(sponsorSet.size() > 1);
    }
}
