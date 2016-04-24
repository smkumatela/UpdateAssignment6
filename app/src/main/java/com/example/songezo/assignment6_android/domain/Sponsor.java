package com.example.songezo.assignment6_android.domain;

import java.io.Serializable;

/**
 * Created by Songezo on 2016-04-14.
 */
public class Sponsor implements Serializable {

    private Long id;
    private String name;
    private String sponsors;

    public Sponsor() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSponsors() {
        return sponsors;
    }

    public static class Builder{
        private Long id;
        private String name;
        private String sponsors;

        public Builder name(String value){
            this.name = value;
            return this;
        }

        public Builder sponsors(String value){
            this.sponsors = value;
            return this;
        }

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder copy(Sponsor value){
            this.id = value.id;
            this.name = value.name;
            this.sponsors = value.sponsors;
            return this;
        }

        public Sponsor build(){
            return new Sponsor(this);
        }
    }

    public Sponsor(Builder builder) {
        name = builder.name;
        sponsors = builder.sponsors;
        id = builder.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor sponsor = (Sponsor) o;

        return !(id != null ? !id.equals(sponsor.id) : sponsor.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
