package com.example.songezo.assignment6_android.factoriesTests;

import com.example.songezo.assignment6_android.domain.Player;
import com.example.songezo.assignment6_android.factories.Player_Factory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Songezo on 2016-04-03.
 */
public class PlayerTest {

    private Player player;
    private Player_Factory player_factory;
    private Map<String, String> values;

    @Before
    public void setUp() throws Exception {
        player_factory = new Player_Factory();
        values = new HashMap<String, String>();
        player = player_factory.getPlayerStandard("Professional");

        values.put("Player Standard", "Player State");
        values.put("development player", "Armature Player");
        values.put("Professional Player", "Advanced Player");


    }

    @Test
    public void testCreatePlayer() throws Exception {
        Player_Factory player = Player_Factory.createPlayer(values);
        Assert.assertEquals("Armature Player", player.getPlayerStandard("Armature").playerStandard());
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        Player_Factory player = Player_Factory.createPlayer(values);
        Player_Factory newPlayer = new Player_Factory.Builder(values.get("Development Teams"))
                .developmentPlayer(values.get("Armature")).copy(player)
                .proffssionalPlayer(values.get("Advanced Player"))
                .build();

        Assert.assertEquals("Armature Player", player.getPlayerStandard("Development Teams").playerStandard());
        Assert.assertEquals("Armature Player", newPlayer.getPlayerStandard("Professional Teams").playerStandard());
    }
}
