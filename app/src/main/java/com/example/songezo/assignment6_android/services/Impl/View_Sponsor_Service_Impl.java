package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Sponsor;
import com.example.songezo.assignment6_android.factories.Sponsor_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Sponsor_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Sponsor_Repository;
import com.example.songezo.assignment6_android.services.View_Sponsors_Service;

import java.util.Map;
import java.util.Set;

public class View_Sponsor_Service_Impl extends Service implements View_Sponsors_Service {

    private final Sponsor_Repository sponsorRepository;

    private final IBinder localBinder = new View_Sponsor_Service_LocalBinder();

    private static View_Sponsor_Service_Impl service = null;

    public static View_Sponsor_Service_Impl getInstance(){
        if (service == null)
            service = new View_Sponsor_Service_Impl();
        return service;
    }

    public View_Sponsor_Service_Impl() {
        sponsorRepository = new Sponsor_Repository_Impl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return localBinder;
    }

    public class View_Sponsor_Service_LocalBinder extends Binder{
        public View_Sponsor_Service_Impl getService(){
            return View_Sponsor_Service_Impl.this;
        }
    }

    @Override
    public Sponsor findById(Long id) {
        return sponsorRepository.findById(id);
    }

    @Override
    public Sponsor save(Sponsor entity) {
        return sponsorRepository.save(entity);
    }

    @Override
    public Set<Sponsor> findAll() {
        return sponsorRepository.findAll();
    }


}
