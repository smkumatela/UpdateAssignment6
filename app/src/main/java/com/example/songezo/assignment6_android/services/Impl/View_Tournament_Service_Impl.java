package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Tournaments;
import com.example.songezo.assignment6_android.factories.Tournament_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Tournaments_Repository_impl;
import com.example.songezo.assignment6_android.repository.Tournaments_Repository;
import com.example.songezo.assignment6_android.services.View_Tournaments_Service;

import java.util.Set;

public class View_Tournament_Service_Impl extends Service implements View_Tournaments_Service {

    private final Tournaments_Repository tournamentsRepository;

    private final IBinder localBinder = new View_Tournament_Service_localBinder();

    private static View_Tournament_Service_Impl service = null;

    public static View_Tournament_Service_Impl getInstance(){
        if (service == null)
            service = new View_Tournament_Service_Impl();
        return service;
    }

//    public View_Tournament_Service_Impl(Tournaments_Repository tournamentsRepository) {
//        this.tournamentsRepository = tournamentsRepository;
//    }

    public View_Tournament_Service_Impl() {
        tournamentsRepository = new Tournaments_Repository_impl(App.getAppContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class View_Tournament_Service_localBinder extends Binder{
        public View_Tournament_Service_Impl getService(){
            return View_Tournament_Service_Impl.getInstance();
        }
    }

    @Override
    public Tournaments findById(Long id) {
        return tournamentsRepository.findById(id);
    }

    @Override
    public Tournaments save(Tournaments entity) {
        return tournamentsRepository.save(entity);
    }

    @Override
    public Set<Tournaments> findAll() {
        return tournamentsRepository.findAll();
    }
}
