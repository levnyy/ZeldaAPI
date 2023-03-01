package com.zelda.ZeldaAPI.model;

public class Creatures {
    private Integer id_creatures;

    private String name;

    private String use;

    private Integer strength;

    private Integer durability;

    public Integer getId_creatures() {
        return id_creatures;
    }

    public void setId_creatures(Integer id_creatures) {
        this.id_creatures = id_creatures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDurability() {
        return durability;
    }

    public void setDurability(Integer durability) {
        this.durability = durability;
    }
}
