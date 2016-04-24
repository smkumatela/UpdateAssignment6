package com.example.songezo.assignment6_android.test_repository;

import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.domain.Team;
import com.example.songezo.assignment6_android.repository.Impl.Team_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Team_Repository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Songezo on 2016-04-23.
 */
public class Team_Repository_Test extends AndroidTestCase {

    private static final String TAG = "TEAM TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Team_Repository repo = new Team_Repository_Impl(this.getContext());

        // CREATE ENTITY
        Team createEntity = new Team.Builder()
                .teamName("Wits")
                .teamNickName("Students")
                .teamLocation("Pretoria")
                .build();
        Team insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ENTITY", insertedEntity);

        // READ ALL
        Set<Team> teamSet = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", teamSet.size()>0);

        // READ ENTITY
        Team entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        // UPDATE ENTITY
        Team updateEntity = new Team.Builder()
                .copy(entity)
                .teamLocation("Pretoria")
                .build();
        repo.update(updateEntity);
        Team newEntity = repo.findById(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY", "Pretoria", newEntity.getTeamLocation());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Team deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE", deletedEntity);
    }
}
