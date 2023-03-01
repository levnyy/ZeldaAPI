package com.zelda.ZeldaAPI.model;

public class Locations {
    private Integer id_locations;

    private String name;

    private String rewards;

    public Integer getId_locations() {
        return id_locations;
    }

    public void setId_locations(Integer id_locations) {
        this.id_locations = id_locations;
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
