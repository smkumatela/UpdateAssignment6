package com.example.songezo.assignment6_android.test_repository;

import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.domain.PremierSoccerLeague;
import com.example.songezo.assignment6_android.repository.Impl.PremierSoccerLeague_Repository_Impl;
import com.example.songezo.assignment6_android.repository.PremierSoccerLeague_Repository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Songezo on 2016-04-23.
 */
public class PremierSoccerLeague_Repository_Test extends AndroidTestCase {

    private static final String TAG = "PREMIER_SOCCER_LEAGUE TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        PremierSoccerLeague_Repository repo = new PremierSoccerLeague_Repository_Impl
                (this.getContext());
        // CREATE ENTITY
        PremierSoccerLeague createEntity = new PremierSoccerLeague.Builder()
                .leagueName("Absa Premiership")
                .abbreviation("PSL")
                .stadium("Soccer City")
                .build();
        PremierSoccerLeague insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE", insertedEntity);

        // READ ALL
        Set<PremierSoccerLeague> leagues = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", leagues.size()>0);

        // READ ENTITY
        PremierSoccerLeague entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        // UPDATE ENTITY
        PremierSoccerLeague updateEntity = new PremierSoccerLeague.Builder()
                .copy(entity)
                .stadium("Moses Mabida")
                .build();
        repo.update(updateEntity);
        PremierSoccerLeague newEntity = repo.findById(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY", "Moses Mabida", newEntity.getStadiums());

        // DELETE ENTITY
        repo.delete(updateEntity);
        PremierSoccerLeague deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE ENTITY", deletedEntity);
    }
}
