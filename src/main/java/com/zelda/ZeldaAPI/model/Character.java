package com.zelda.ZeldaAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Character {
    @Id
    private Integer id_character;

    private String name;

    private String gender;

    private String race;

    public Integer getId_character() {
        return id_character;
    }
    public void setId_character(Integer id_character) {
        this.id_character = id_character;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
}