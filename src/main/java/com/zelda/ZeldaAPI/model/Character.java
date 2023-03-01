package com.zelda.ZeldaAPI.model;


public class Character {
    private Integer id_character;

    private String name;

    private String gender;

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
}
