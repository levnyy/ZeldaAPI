package com.zelda.ZeldaAPI.controller.service;

import com.zelda.ZeldaAPI.model.Location;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class LocationsService {
    public Iterable<Location> findAll() {
        return characterRepository.findAll();
    }
    public Location findById(Integer ID) {
        Optional<Location> character = bossesRepository.findById(ID);
        return character.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<Location> findByName (String name){
        return characterRepository.findByName(name);
    }
    public Iterable<Location> findByGender(String gender) {
        return characterRepository.findByGender(gender);
    }
    public Iterable<Location> findByRace(String race) {
        return characterRepository.findByRace(rac e);
    }
    public void  update (Location location){
        characterRepository.save(location);
    }
    public void  deleteById (Integer ID){
        characterRepository.deleteById(ID);
    }
}

}
