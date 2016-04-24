package com.example.songezo.assignment6_android.test_repository;

import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.domain.Log_Standings;
import com.example.songezo.assignment6_android.repository.Impl.Log_Standings_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Log_Standings_Repository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Songezo on 2016-04-22.
 */
public class Log_Standings_Repository_Test extends AndroidTestCase {

    private static final String TAG = "LOG_STANDINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Log_Standings_Repository repo = new Log_Standings_Repository_Impl(this.getContext());

        // CREATE ENTITY
        Log_Standings createEntity = new Log_Standings.Builder()
                .teamName("Chiefs")
                .gamesPlayed(5)
                .gamesWon(3)
                .gamesLost(1)
                .gamesDrawn(1)
                .build();
        Log_Standings insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotSame(TAG+" CREATE", insertedEntity);

        Set<Log_Standings> log_standingsSet = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", log_standingsSet.size()>0);

        // READ ENTITY
        Log_Standings entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        // UPDATE ENTITY
        Log_Standings updateEntity = new Log_Standings.Builder()
                .copy(entity)
                .gamesPlayed(7)
                .build();
        repo.update(updateEntity);
        Log_Standings newEntity = repo.findById(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY", 7, newEntity.getGamesPlayed());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Log_Standings deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE", deletedEntity);
    }
}
