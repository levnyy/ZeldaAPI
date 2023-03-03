package com.zelda.ZeldaAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Location {
    @Id
    private Integer id_location;
    @NotNull
    private String name;

    private String rewards;

    public Integer getId_location() {
        return id_location;
    }
    public void setId_location(Integer id_location) {
        this.id_location = id_location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRewards() {
        return rewards;
    }
    public void setRewards(String rewards) {
        this.rewards = rewards;
    }
}