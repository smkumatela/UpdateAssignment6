package com.example.songezo.assignment6_android.test_repository;

import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.repository.Impl.Stadium_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Stadium_Repository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Songezo on 2016-04-23.
 */
public class Stadium_Repository_Test extends AndroidTestCase {

    private static final String TAG = "STADIUM TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Stadium_Repository repo = new Stadium_Repository_Impl(this.getContext());

        // CREATE ENTITY
        Stadium createEntity = new Stadium.Builder()
                .name("Stanford Bridge")
                .location("London")
                .build();
        Stadium insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ENTITY", insertedEntity);

        // READ ALL
        Set<Stadium> stadiumSet = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", stadiumSet.size()>0);

        // READ ENTITY
        Stadium entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        // UPDATE ENTITY
        Stadium updateEntity = new Stadium.Builder()
                .copy(entity)
                .location("England")
                .build();
        repo.update(updateEntity);
        Stadium newEntity = repo.findById(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY", "London", newEntity.getLocation());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Stadium deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE", deletedEntity);
    }
}
