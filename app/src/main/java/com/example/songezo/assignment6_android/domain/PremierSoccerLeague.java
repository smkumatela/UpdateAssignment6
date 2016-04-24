package com.example.songezo.assignment6_android.domain;

import java.io.Serializable;

/**
 * Created by Songezo on 2016-04-14.
 */
public class PremierSoccerLeague implements Serializable{

    private Long id;
    private String leagueName;
    private String abbreviation;
    private String stadiums;

    public PremierSoccerLeague() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setStadiums(String stadiums) {
        this.stadiums = stadiums;
    }

    public Long getId() {
        return id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getStadiums() {
        return stadiums;
    }

    public static class Builder{
        private String leagueName;
        private String abbreviation;
        private Long id;
        private String stadiums;

        public Builder leagueName(String value){
            this.leagueName = value;
            return this;
        }

        public Builder abbreviation(String value){
            this.abbreviation = value;
            return this;
        }

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder stadium(String value){
            this.stadiums = value;
            return this;
        }

        public Builder copy(PremierSoccerLeague value){
            this.leagueName = value.leagueName;
            this.abbreviation = value.abbreviation;
            this.id = value.id;
            this.stadiums = value.stadiums;
            return this;
        }

        public PremierSoccerLeague build(){
            return new PremierSoccerLeague(this);
        }
    }

    public PremierSoccerLeague(Builder builder){
        id = builder.id;
        leagueName = builder.leagueName;
        abbreviation = builder.abbreviation;
        stadiums = builder.stadiums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PremierSoccerLeague that = (PremierSoccerLeague) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
