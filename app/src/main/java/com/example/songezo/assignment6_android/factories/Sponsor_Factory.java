package com.example.songezo.assignment6_android.factories;

import com.example.songezo.assignment6_android.domain.Sponsor;

import java.util.Map;

/**
 * Created by Songezo on 2016-04-14.
 */
public class Sponsor_Factory {

    private Long id;
    private String name;
    private String sponsors;

    public static Sponsor_Factory sponsor = null;

    public Sponsor_Factory() {
    }

    public static Sponsor_Factory getSponsorInstance(){
        if (sponsor == null){
            sponsor = new Sponsor_Factory();
        }
        return sponsor;
    }

    public static Sponsor createSponsor(Map<String, String> value, Long id){
        Sponsor sponsor = new Sponsor.Builder()
                .name(value.get("name"))
                .sponsors(value.get("sponsors"))
                .id(12349L)
                .build();

        return sponsor;
    }
}
