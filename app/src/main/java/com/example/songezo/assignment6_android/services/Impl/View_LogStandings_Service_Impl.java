package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.domain.Log_Standings;
import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.factories.Log_Standings_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Log_Standings_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Log_Standings_Repository;
import com.example.songezo.assignment6_android.services.View_LogStandings_Services;

import java.util.Map;

public class View_LogStandings_Service_Impl extends Service implements View_LogStandings_Services {

    private final IBinder localBinder = new View_LogStandings_Service_LocalBinder();
    private Log_Standings_Repository repo;

    public View_LogStandings_Service_Impl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class View_LogStandings_Service_LocalBinder extends Binder{
        public View_LogStandings_Service_Impl getLogStandings() {
            return View_LogStandings_Service_Impl.this;
        }
    }

    @Override
    public String viewLog(Map<String, Integer> values, Long id) {
        if (true){
            Log_Standings log_standings = Log_Standings_Factory.createLogStandings(values, id);
            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public Boolean isLogViewed() {
        return repo.findAll().size() > 0;
    }

    @Override
    public Boolean destroyLog() {
        int rows = repo.deleteAll();
        return rows > 0;
    }

    private Log_Standings createLogStandings(Log_Standings log_standings){
        repo = new Log_Standings_Repository_Impl(App.getAppContext());
        return repo.save(log_standings);
    }
}
