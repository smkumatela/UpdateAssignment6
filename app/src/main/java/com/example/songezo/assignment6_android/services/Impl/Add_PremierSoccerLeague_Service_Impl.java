package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.conf.util.GlobalContext;
import com.example.songezo.assignment6_android.domain.PremierSoccerLeague;
import com.example.songezo.assignment6_android.factories.PremierSoccerLeague_Factory;
import com.example.songezo.assignment6_android.repository.Impl.PremierSoccerLeague_Repository_Impl;
import com.example.songezo.assignment6_android.repository.PremierSoccerLeague_Repository;
import com.example.songezo.assignment6_android.services.Add_PremierSoccerLeague_Service;

import java.util.Map;
import java.util.Set;

public class Add_PremierSoccerLeague_Service_Impl extends Service implements Add_PremierSoccerLeague_Service {

    private final PremierSoccerLeague_Repository pslRepository;

    private final IBinder localBinder = new AddPremierSoccerLeagueLocalBinder();

    private static Add_PremierSoccerLeague_Service_Impl service = null;

    public static Add_PremierSoccerLeague_Service_Impl getInstance(){
        if (service == null)
            service = new Add_PremierSoccerLeague_Service_Impl();
        return service;
    }

    public Add_PremierSoccerLeague_Service_Impl() {
        pslRepository = new PremierSoccerLeague_Repository_Impl(App.getAppContext());
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
    public PremierSoccerLeague findById(Long id) {
        return pslRepository.findById(id);
    }

    @Override
    public PremierSoccerLeague save(PremierSoccerLeague entity) {
        return pslRepository.save(entity);
    }

    @Override
    public Set<PremierSoccerLeague> findAll() {
        return pslRepository.findAll();
    }

}
