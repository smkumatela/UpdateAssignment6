package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.factories.Stadium_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Stadium_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Stadium_Repository;
import com.example.songezo.assignment6_android.services.View_Stadiums_Service;

import java.util.Map;

public class View_Stadium_Service_Impl extends Service implements View_Stadiums_Service{

    private final IBinder localBinder = new View_Stadium_Service_LocalBinder();
    private Stadium_Repository repo;

    public View_Stadium_Service_Impl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return localBinder;
    }

    public class View_Stadium_Service_LocalBinder extends Binder{
        public View_Stadium_Service_Impl getStadium() {
            return View_Stadium_Service_Impl.this;
        }
    }

    @Override
    public String viewStadium(Map<String, String> values, Long id) {
        if (true){
            Stadium stadium = Stadium_Factory.createStadium(values, id);
            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public Boolean isStadiumViewed() {
        return repo.findAll().size() > 0;
    }

    @Override
    public Boolean destroyStadium() {
        int rows = repo.deleteAll();
        return rows > 0;
    }

    private Stadium createStadium(Stadium stadium){
        repo = new Stadium_Repository_Impl(App.getAppContext());
        return repo.save(stadium);
    }

}
