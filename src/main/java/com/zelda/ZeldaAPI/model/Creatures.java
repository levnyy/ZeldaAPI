package com.zelda.ZeldaAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Creatures {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_creatures;
    @NotNull
    private String name;

    private Integer strength;

    private Integer durability;

    @NotNull
    private Integer id_location;

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
