package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.Log_Standings;
import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.factories.Log_Standings_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Log_Standings_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Log_Standings_Repository;
import com.example.songezo.assignment6_android.services.View_LogStandings_Services;

import java.util.Map;
import java.util.Set;

public class View_LogStandings_Service_Impl extends Service implements View_LogStandings_Services {

    private final Log_Standings_Repository logStandingsRepository;

    private final IBinder localBinder = new View_LogStandings_Service_LocalBinder();

    private static View_LogStandings_Service_Impl service = null;

    public static View_LogStandings_Service_Impl getInstance(){
        if (service == null)
            service = new View_LogStandings_Service_Impl();
        return service;
    }

    public View_LogStandings_Service_Impl() {
        logStandingsRepository = new Log_Standings_Repository_Impl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return localBinder;
    }

    public class View_LogStandings_Service_LocalBinder extends Binder{
        public View_LogStandings_Service_Impl getLogStandings() {
            return View_LogStandings_Service_Impl.this;
        }
    }

    @Override
    public Log_Standings findById(Long id) {
        return logStandingsRepository.findById(id);
    }

    @Override
    public Log_Standings save(Log_Standings entity) {
        return logStandingsRepository.save(entity);
    }

    @Override
    public Set<Log_Standings> findAll() {
        return logStandingsRepository.findAll();
    }

}
