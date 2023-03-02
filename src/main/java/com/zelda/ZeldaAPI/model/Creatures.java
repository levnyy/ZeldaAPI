package com.zelda.ZeldaAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Creatures {
    @Id
    private Integer id_creatures;

    private String name;

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
