package com.example.songezo.assignment6_android.domain;

import java.io.Serializable;

/**
 * Created by Songezo on 2016-04-14.
 */
public class Stadium implements Serializable {

    private Long id;
    private String name;
    private String location;

    public Stadium() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public static class Builder{
        private Long id;
        private String name;
        private String location;

        public Builder name(String value){
            this.name = value;
            return this;
        }

        public Builder location(String value){
            this.location = value;
            return this;
        }

        public Builder id(long value){
            this.id = id;
            return this;
        }

        public Builder copy(Stadium value){
            id = value.id;
            name = value.name;
            location = value.location;
            return this;
        }

        public Stadium build(){
            return new Stadium(this);
        }
    }

    public Stadium(Builder builder){
        id = builder.id;
        name = builder.name;
        location = builder.location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stadium stadium = (Stadium) o;

        return !(id != null ? !id.equals(stadium.id) : stadium.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
