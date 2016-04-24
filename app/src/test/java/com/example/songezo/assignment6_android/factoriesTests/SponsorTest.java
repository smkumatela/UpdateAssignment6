package com.example.songezo.assignment6_android.factoriesTests;

import com.example.songezo.assignment6_android.domain.Sponsor;
import com.example.songezo.assignment6_android.factories.Sponsor_Factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Songezo on 2016-04-24.
 */
public class SponsorTest {
    private Long id;
    private Sponsor sponsor;
    private Sponsor_Factory sponsor_factory;
    private Map<String, String> values;

    @Before
    public void setUp() throws Exception {
        values = new HashMap<>();
        //sponsor = new Sponsor();
        //sponsor_factory = new Sponsor_Factory();

        values.put("name", "Nike");
        values.put("sponsors", "Ronaldo");
    }

    @Test
    public void testSponsor() throws Exception {
        Sponsor spon = Sponsor_Factory.createSponsor(values, id);
        Assert.assertEquals("Nike", spon.getName());
    }

    @Test
    public void testUpdateSponsor() throws Exception {
        Sponsor spon = Sponsor_Factory.createSponsor(values, id);
        Sponsor newSponsor = new Sponsor.Builder()
                .copy(spon)
                .name("Adidas")
                .build();

        Assert.assertEquals("Adidas", newSponsor.getName());
    }
}
