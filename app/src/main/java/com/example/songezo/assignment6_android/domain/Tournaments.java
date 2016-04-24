package com.example.songezo.assignment6_android.domain;

import java.io.Serializable;

/**
 * Created by Songezo on 2016-04-14.
 */
public class Tournaments implements Serializable {

    private Long id;
    private String tName;
    private int numOfTeams;
    private double prizeMoney;

    public Tournaments() {
    }

    public Long getId() {
        return id;
    }

    public String gettName() {
        return tName;
    }

    public int getNumOfTeams() {
        return numOfTeams;
    }

    public double getPrizeMoney() {
        return prizeMoney;
    }

    public static class Builder{
        private Long id;
        private String tName;
        private int numOfTeams;
        private double prizeMoney;

        public Builder tName(String value){
            this.tName = value;
            return this;
        }

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder numOfTeams(int value){
            this.numOfTeams = value;
            return this;
        }

        public Builder prizeMoney(double value){
            this.prizeMoney = value;
            return this;
        }

        public Builder copy(Tournaments value){
            this.prizeMoney = value.prizeMoney;
            this.numOfTeams = value.numOfTeams;
            this.id = value.id;
            this.tName = value.tName;
            return this;
        }

        public Tournaments build(){
            return new Tournaments(this);
        }
    }

    public Tournaments(Builder builder){
        prizeMoney = builder.prizeMoney;
        numOfTeams = builder.numOfTeams;
        id = builder.id;
        tName = builder.tName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tournaments that = (Tournaments) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
