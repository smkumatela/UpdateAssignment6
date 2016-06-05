package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.factories.Stadium_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Stadium_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Stadium_Repository;
import com.example.songezo.assignment6_android.services.View_Stadiums_Service;

import java.util.Map;
import java.util.Set;

public class View_Stadium_Service_Impl extends Service implements View_Stadiums_Service{


    private final Stadium_Repository stadiumRepository;

    private final IBinder localBinder = new View_Stadium_Service_LocalBinder();

    private static View_Stadium_Service_Impl service = null;

    public static View_Stadium_Service_Impl getInstance(){
        if (service == null)
            service = new View_Stadium_Service_Impl();
        return service;
    }

    public View_Stadium_Service_Impl() {
        stadiumRepository = new Stadium_Repository_Impl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return localBinder;
    }

    public class View_Stadium_Service_LocalBinder extends Binder{
        public View_Stadium_Service_Impl getService() {
            return View_Stadium_Service_Impl.getInstance();
        }
    }

    @Override
    public Stadium findById(Long id) {
        return stadiumRepository.findById(id);
    }

    @Override
    public Stadium save(Stadium entity) {
        return stadiumRepository.save(entity);
    }

    @Override
    public Set<Stadium> findAll() {
        return stadiumRepository.findAll();
    }

}
