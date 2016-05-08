package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.domain.Tournaments;
import com.example.songezo.assignment6_android.factories.Tournament_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Tournaments_Repository_impl;
import com.example.songezo.assignment6_android.repository.Tournaments_Repository;
import com.example.songezo.assignment6_android.services.View_Tournaments_Service;

public class View_Tournament_Service_Impl extends Service implements View_Tournaments_Service {

    private final IBinder localBinder = new View_Tournaments_Service_LocalBinder();

    private Tournaments_Repository repository;

    public View_Tournament_Service_Impl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return localBinder;
    }

    public class View_Tournaments_Service_LocalBinder extends Binder{
        public View_Tournament_Service_Impl getTournament(){
            return View_Tournament_Service_Impl.this;
        }
    }

    @Override
    public String viewTournaments(String name, int numOfTeams, double prize, Long id) {
        if (true){
            Tournaments tournaments = Tournament_Factory
                    .createTournament("Nedbank Cup", 32, 10.000000, 22376L);

            return DomainState.ACTIVATED.name();
        }
        else {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public Boolean isTournamentViewed() {
        return repository.findAll().size() > 0;
    }

    @Override
    public Boolean destroyTournament() {
        int rows = repository.deleteAll();
        return rows > 0;
    }

    private Tournaments createTournaments(Tournaments tournament){
        repository = new Tournaments_Repository_impl(App.getAppContext());
        return repository.save(tournament);
    }
}
