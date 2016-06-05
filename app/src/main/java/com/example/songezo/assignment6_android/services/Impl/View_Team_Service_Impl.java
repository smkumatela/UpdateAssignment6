package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Team;
import com.example.songezo.assignment6_android.factories.Team_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Team_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Team_Repository;
import com.example.songezo.assignment6_android.services.View_Team_Service;

import java.util.Map;
import java.util.Set;

public class View_Team_Service_Impl extends Service implements View_Team_Service {

    private final Team_Repository teamRepository;

    private final IBinder localBinder = new View_Team_Service_LocalBinder();

    private static View_Team_Service_Impl service = null;

    public static View_Team_Service_Impl getInstance(){
        if (service == null)
            service = new View_Team_Service_Impl();
        return service;
    }

    public View_Team_Service_Impl(){
        teamRepository = new Team_Repository_Impl(App.getAppContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class View_Team_Service_LocalBinder extends Binder{
        public View_Team_Service_Impl getService(){
            return View_Team_Service_Impl.getInstance();
        }
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team save(Team entity) {
        return teamRepository.save(entity);
    }

    @Override
    public Set<Team> findAll() {
        return teamRepository.findAll();
    }
}
