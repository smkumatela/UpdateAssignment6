package com.example.songezo.assignment6_android.factories;

import com.example.songezo.assignment6_android.domain.PremierSoccerLeague;

import java.util.Map;

/**
 * Created by Songezo on 2016-04-14.
 */
public class PremierSoccerLeague_Factory {

    private Long id;
    private String leagueName;
    private String abbreviation;
    private String stadiums;

    public static PremierSoccerLeague_Factory psl = null;

    public PremierSoccerLeague_Factory() {
    }

    public static PremierSoccerLeague_Factory getPslInstance(){
        if (psl == null){
            psl = new PremierSoccerLeague_Factory();
        }
        return psl;
    }

    public static class Builder{
        private Long id;
        private String leagueName;
        private String abbreviation;
        private String stadiums;

        public Builder(String leagueName){
            this.leagueName = leagueName;
        }

        public Builder abbreviation(String value){
            this.abbreviation = value;
            return this;
        }

        public Builder studiums(String value){
            this.stadiums = value;
            return this;
        }

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder copy(PremierSoccerLeague_Factory value){
            this.id = value.id;
            this.stadiums = value.stadiums;
            this.abbreviation = value.abbreviation;
            this.leagueName = value.leagueName;
            return this;
        }

        public PremierSoccerLeague_Factory build(){
            return new PremierSoccerLeague_Factory(this);
        }
    }

    public PremierSoccerLeague_Factory(Builder builder){
        id = builder.id;
        stadiums = builder.stadiums;
        abbreviation = builder.abbreviation;
        leagueName = builder.leagueName;
    }

    public static PremierSoccerLeague createPremierSoccerLeague(Map<String, String> values, Long id){
        PremierSoccerLeague premierSoccerLeague = new PremierSoccerLeague
                .Builder()
                .leagueName(values.get("LeagueName"))
                .abbreviation(values.get("abbreviation"))
                .stadium(values.get("stadium"))
                .id(12339L)
                .build();
        return premierSoccerLeague;
    }
}
