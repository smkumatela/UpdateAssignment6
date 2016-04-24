package com.example.songezo.assignment6_android.domain;

/**
 * Created by Songezo on 2016-04-14.
 */
public class Log_Standings {

    private Long id;
    private String teamName;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrawn;
    private int points;

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public int getPoints() {
        return points;
    }

    public static class Builder{
        private Long id;
        private String teamName;
        private int gamesPlayed;
        private int gamesWon;
        private int gamesLost;
        private int gamesDrawn;
        private int points;

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder gamesPlayed(int value){
            this.gamesPlayed = value;
            return this;
        }

        public Builder gamesWon(int value){
            this.gamesWon = value;
            return this;
        }

        public Builder gamesLost(int value){
            this.gamesLost = value;
            return this;
        }

        public Builder gamesDrawn(int value){
            this.gamesDrawn = value;
            return this;
        }

        public Builder points(int value){
            this.points = value;
            return this;
        }

        public Builder teamName(String value){
            this.teamName = value;
            return this;
        }

        public Builder copy(Log_Standings builder){
            this.teamName = builder.teamName;
            this.gamesDrawn = builder.gamesDrawn;
            this.gamesLost = builder.gamesLost;
            this.gamesPlayed = builder.gamesPlayed;
            this.gamesWon = builder.gamesWon;
            this.points = builder.points;
            this.id = builder.id;
            return this;
        }
        public Log_Standings build(){
            return new Log_Standings(this);
        }
    }

    public Log_Standings(Builder builder){
        id = builder.id;
        teamName = builder.teamName;
        gamesWon = builder.gamesWon;
        gamesPlayed = builder.gamesPlayed;
        gamesLost = builder.gamesLost;
        gamesDrawn = builder.gamesDrawn;
        points = builder.points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log_Standings that = (Log_Standings) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
