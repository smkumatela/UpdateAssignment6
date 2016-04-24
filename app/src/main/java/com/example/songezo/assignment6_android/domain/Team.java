package com.example.songezo.assignment6_android.domain;

import java.io.Serializable;

/**
 * Created by Songezo on 2016-04-14.
 */
public class Team implements Serializable{

    private Long id;
    private String teamName;
    private String teamNickName;
    private String teamLocation;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamNickName() {
        return teamNickName;
    }

    public String getTeamLocation() {
        return teamLocation;
    }

    public static class Builder{
        private Long id;
        private String teamName;
        private String teamNickName;
        private String teamLocation;

        public Builder teamName(String value){
            this.teamName = value;
            return this;
        }

        public Builder teamNickName(String value){
            this.teamNickName = value;
            return this;
        }

        public Builder teamLocation(String value){
            this.teamLocation = value;
            return this;
        }

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder copy(Team value){
            this.id = value.id;
            this.teamLocation = value.teamLocation;
            this.teamNickName = value.teamNickName;
            this.teamName = value.teamName;
            return this;
        }

        public Team build(){
            return new Team(this);
        }
    }

    public Team(Builder builder){
        this.id = builder.id;
        this.teamLocation = builder.teamLocation;
        this.teamName = builder.teamName;
        this.teamNickName = builder.teamNickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return !(id != null ? !id.equals(team.id) : team.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
