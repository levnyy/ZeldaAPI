package com.zelda.ZeldaAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bosses {
    @Id

    private Integer id_bosses;

    private String name;

    private Integer health;

    private String weakness;

    private String habitat;

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
    public String getHabitat() {
        return habitat;
    }
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
