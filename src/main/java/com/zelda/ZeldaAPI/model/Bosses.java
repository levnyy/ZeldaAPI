package com.zelda.ZeldaAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Bosses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_bosses;
    @NotNull
    private String name;
    @NotNull
    private Integer health;

    private String weakness;

    public Integer getId_bosses() {
        return id_bosses;
    }
    public void setId_bosses(Integer id_bosses) {
        this.id_bosses = id_bosses;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getHealth() {
        return health;
    }
    public void setHealth(Integer health) {
        this.health = health;
    }
    public String getWeakness() {
        return weakness;
    }
    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }
}
