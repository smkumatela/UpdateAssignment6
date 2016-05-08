package com.example.songezo.assignment6_android.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.conf.util.DomainState;
import com.example.songezo.assignment6_android.domain.Sponsor;
import com.example.songezo.assignment6_android.factories.Sponsor_Factory;
import com.example.songezo.assignment6_android.repository.Impl.Sponsor_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Sponsor_Repository;
import com.example.songezo.assignment6_android.services.View_Sponsors_Service;

import java.util.Map;

public class View_Sponsor_Service_Impl extends Service implements View_Sponsors_Service {

    private final IBinder localBinder = new View_Sponsor_Service_LocalBinder();
    private Sponsor_Repository repo;

    public View_Sponsor_Service_Impl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return localBinder;
    }

    public class View_Sponsor_Service_LocalBinder extends Binder{
        public View_Sponsor_Service_Impl getSponsor(){
            return View_Sponsor_Service_Impl.this;
        }
    }

    @Override
    public String viewSponsor(Map<String, String> values, Long id) {
        if (true){
            Sponsor sponsor = Sponsor_Factory
                    .createSponsor(values, id);
            return DomainState.ACTIVATED.name();
        }
        else {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public Boolean isSponsorViewed() {
        return repo.findAll().size() > 0;
    }

    @Override
    public Boolean destroySponsor() {
        int rows = repo.deleteAll();
        return rows > 0;
    }

    private Sponsor createSponsor(Sponsor sponsor){
        repo = new Sponsor_Repository_Impl(App.getAppContext());
        return repo.save(sponsor);
    }
}
