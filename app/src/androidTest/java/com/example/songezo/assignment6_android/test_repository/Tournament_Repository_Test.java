package com.example.songezo.assignment6_android.test_repository;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.conf.util.App;
import com.example.songezo.assignment6_android.domain.Tournaments;
import com.example.songezo.assignment6_android.repository.Impl.Tournaments_Repository_impl;
import com.example.songezo.assignment6_android.repository.Tournaments_Repository;
import com.example.songezo.assignment6_android.services.Impl.View_LogStandings_Service_Impl;
import com.example.songezo.assignment6_android.services.Impl.View_Tournament_Service_Impl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Songezo on 2016-04-23.
 */
public class Tournament_Repository_Test extends AndroidTestCase {

    private static final String TAG = "TOURNAMENTS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Tournaments_Repository repo = new Tournaments_Repository_impl(this.getContext());

        // CREATE ENTITY
        Tournaments createEntity = new Tournaments.Builder()
                .tName("Nedbank Cup")
                .numOfTeams(32)
                .prizeMoney(10000000.000)
                .build();
        Tournaments insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ENTITY", insertedEntity);

        // READ ALL
        Set<Tournaments> tournamentsSet = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", tournamentsSet.size()>0);

        // READ ENTITY
        Tournaments entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        // UPDATE ENTITY
        Tournaments updateEntity = new Tournaments.Builder()
                .copy(entity)
                .prizeMoney(15000000.000)
                .build();
        repo.update(updateEntity);
        Tournaments newEntity = repo.findById(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY", 15000000.000, newEntity.getPrizeMoney());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Tournaments deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE ENTITY", deletedEntity);
    }

}
