package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.domain.Team;
import com.example.songezo.assignment6_android.factories.Team_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Team_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Team_Repository;
import com.example.songezo.assignment6_android.services.View_Team_Service;

import java.util.Map;

public class View_Team_Service_Impl extends Service implements View_Team_Service {

    private final IBinder localBinder = new View_Team_Service_LocalBinder();
    private Team_Repository repo;

    public View_Team_Service_Impl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return localBinder;
    }

    public class View_Team_Service_LocalBinder extends Binder{
        public View_Team_Service_Impl getTeam(){
            return View_Team_Service_Impl.this;
        }
    }

    @Override
    public String viewTeam(Map<String, String> values, Long id) {
        if (true){
            Team team = Team_Factory
                    .createTeam(values, id);
            return DomainState.ACTIVATED.name();
        }
        else {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public Boolean isTeamViewed() {
        return repo.findAll().size() > 0;
    }

    @Override
    public Boolean destroyTeam() {
        int rows = repo.deleteAll();
        return rows > 0;
    }

    private Team createTeam(Team team){
        repo = new Team_Repository_Impl(App.getAppContext());
        return repo.save(team);
    }
}
