package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.domain.PremierSoccerLeague;
import com.example.songezo.assignment6_android.factories.PremierSoccerLeague_Factory;
import com.example.songezo.assignment6_android.repository.Impl.PremierSoccerLeague_Repository_Impl;
import com.example.songezo.assignment6_android.repository.PremierSoccerLeague_Repository;
import com.example.songezo.assignment6_android.services.Add_PremierSoccerLeague_Service;

import java.util.Map;

public class Add_PremierSoccerLeague_Service_Impl extends Service implements Add_PremierSoccerLeague_Service {

    private final IBinder localBinder = new AddPremierSoccerLeagueLocalBinder();

    private PremierSoccerLeague_Repository repo;

    public Add_PremierSoccerLeague_Service_Impl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return localBinder;
    }

    public class AddPremierSoccerLeagueLocalBinder extends Binder{
        public Add_PremierSoccerLeague_Service_Impl getPremierSoccerLeague(){
            return Add_PremierSoccerLeague_Service_Impl.this;
        }
    }

    @Override
    public String create_league(Map<String, String> values) {
        if (true){
            PremierSoccerLeague premierSoccerLeague = PremierSoccerLeague_Factory
                    .createPremierSoccerLeague(values);
            return DomainState.ACTIVATED.name();
        }
        else {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isLeagueCreated() {
        return repo.findAll().size() > 0;
    }

    @Override
    public boolean destroyLeague() {
        int rows = repo.deleteAll();
        return rows > 0;
    }

    private PremierSoccerLeague createPremierSoccerrLeague(PremierSoccerLeague premierSoccerLeagues){
        repo = new PremierSoccerLeague_Repository_Impl(App.getAppContext());
        return repo.save(premierSoccerLeagues);
    }

}
