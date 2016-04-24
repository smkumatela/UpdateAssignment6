package com.example.songezo.assignment6_android.factoriesTests;
import junit.framework.Assert;
//import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.example.songezo.assignment6_android.domain.Broadcaster;
import com.example.songezo.assignment6_android.factories.Broadcaster_Factory;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Songezo on 2016-04-03.
 */
public class BroadcasterTest {

    private Broadcaster broadcaster;
    private Broadcaster_Factory broadcaster_fact, newBr;
    private Map<String, String> values;

    @Before
    public void setUp() throws Exception {
        broadcaster_fact = new Broadcaster_Factory();
        values = new HashMap<String, String>();

        values.put("type of match", "Cup Game");
        values.put("type of match", "Nedbank Cup");


    }
    @Test
    public void testCreateBroadcaster() throws Exception {
        Broadcaster_Factory broadcaster_factory = Broadcaster_Factory.createBoadcaster(values);
        Assert.assertEquals("Nedbank Cup", broadcaster_factory.getTypeOfMatchBroadcasted("Cup Game").typeOfMatch());
    }

    @Test
    public void testUpdateBroadcaster() throws Exception {
        Broadcaster_Factory broadcaster_factory = Broadcaster_Factory.createBoadcaster(values);
        Broadcaster_Factory broadcaste12 = new Broadcaster_Factory.Builder(values.get("League Match"))
                .cupGame(values.get("Cup Game")).copy(broadcaster_factory).cupGame(values.get("type of match"))
                .build();


        Assert.assertEquals("Nedbank Cup", broadcaster_factory.getTypeOfMatchBroadcasted("Cup Game").typeOfMatch());
        Assert.assertEquals("Nedbank Cup", broadcaste12.getTypeOfMatchBroadcasted("Cup Game").typeOfMatch());
    }

}
