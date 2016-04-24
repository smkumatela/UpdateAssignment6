package com.example.songezo.assignment6_android.test_repository;

import android.test.AndroidTestCase;

import com.example.songezo.assignment6_android.domain.Sponsor;
import com.example.songezo.assignment6_android.repository.Impl.Sponsor_Repository_Impl;
import com.example.songezo.assignment6_android.repository.Sponsor_Repository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Songezo on 2016-04-23.
 */
public class Sposor_Repository_Test extends AndroidTestCase {
    private static final String TAG = "SPONSOR TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Sponsor_Repository repo = new Sponsor_Repository_Impl(this.getContext());

        // CREATE ENTITY
        Sponsor createEntity = new Sponsor.Builder()
                .name("Nike")
                .sponsors("Ronaldo")
                .build();
        Sponsor insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE", insertedEntity);

        // READ ALL
        Set<Sponsor> sponsorSet = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", sponsorSet.size()>0);

        // READ ENTITY
        Sponsor entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        // UPDATE ENTITY
        Sponsor updateEntity = new Sponsor.Builder()
                .sponsors("Katsande")
                .build();
        repo.update(updateEntity);
        Sponsor newEntity = repo.findById(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY", "Ronaldo", newEntity.getSponsors());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Sponsor deletedEntity = repo.findById(id);
        Assert.assertNotNull(TAG+" DELETE", deletedEntity);
    }
}
