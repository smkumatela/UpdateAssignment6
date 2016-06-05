package com.example.songezo.assignment6_android.test_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.provider.Settings;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.domain.Team;
import com.example.songezo.assignment6_android.factories.Stadium_Factory;
import com.example.songezo.assignment6_android.repository.Stadium_Repository;
import com.example.songezo.assignment6_android.services.Impl.View_Stadium_Service_Impl;

import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Songezo on 2016-05-08.
 */
public class View_Stadiums_Service_Test extends AndroidTestCase {
    private View_Stadium_Service_Impl stadiumService;
    private Boolean isBound;
    private static final String TAG = "STADIUM TEST";

    Map<String, String> values;
    Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), View_Stadium_Service_Impl.class);
        GlobalContext.context = this.getContext();
        stadiumService = View_Stadium_Service_Impl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        values = new HashMap<>();
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            View_Stadium_Service_Impl.View_Stadium_Service_LocalBinder binder
                    = (View_Stadium_Service_Impl.View_Stadium_Service_LocalBinder) service;
            stadiumService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntity() throws Exception {
        values.put("name","Moses Mabida");
        values.put("location","Durban");
        id = 12390L;
        Stadium stadiumEntity = Stadium_Factory.createStadium(values, id);
        Stadium newStadium = stadiumService.save(stadiumEntity);
        Assert.assertNotNull(newStadium);
    }

    public void testCreateAndFindListOfEntities() throws Exception {
        values.put("name","Nelson Mandela Bay");
        values.put("location","Part Elizabeth");
        id = 123101L;
        Stadium stadium1 = Stadium_Factory.createStadium(values, id);
        stadiumService.save(stadium1);

        values.put("name","Soccer City");
        values.put("location","Soweto");
        id = 12315L;
        Stadium stadium2 = Stadium_Factory.createStadium(values, id);
        stadiumService.save(stadium2);

        Set<Stadium> stadiumSet = stadiumService.findAll();
        Assert.assertTrue(stadiumSet.size() > 1);

    }
}
