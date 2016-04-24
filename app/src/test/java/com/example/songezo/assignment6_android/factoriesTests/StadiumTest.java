package com.example.songezo.assignment6_android.factoriesTests;

import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.factories.Stadium_Factory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Songezo on 2016-04-03.
 */
public class StadiumTest {

    private Long id;
    private Stadium stadium;
    private Stadium_Factory stadium_factory;
    private Map<String, String> values;

    @Before
    public void setUp() throws Exception {
        values = new HashMap<String, String>();
        stadium = new Stadium();
        stadium_factory = new Stadium_Factory();

        values.put("name", "Moses Mabida");
        values.put("location", "durban");
    }

    @Test
    public void testCreateStadium() throws Exception {
        Stadium stadium = Stadium_Factory.createStadium(values, id);
        Assert.assertEquals("Moses Mabida", stadium.getName());
    }

    @Test
    public void testUpdateStadium() throws Exception {
        Stadium stadium = Stadium_Factory.createStadium(values, id);
        Stadium newStadium = new Stadium.Builder(

        ).copy(stadium).location("London")
                .build();
        Assert.assertEquals("Moses Mabida", newStadium.getName());
        Assert.assertEquals("London", newStadium.getLocation());
        Assert.assertEquals("Moses Mabida", stadium.getName());
    }
}
