package com.zelda.ZeldaAPI.controller.service;

import com.zelda.ZeldaAPI.controller.repository.CharacterRepository;
import com.zelda.ZeldaAPI.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
    public Iterable<Character> findAll() {
        return characterRepository.findAll();
    }
    public Character findById(Integer ID) {
        Optional<Location> character = bossesRepository.findById(ID);
        return character.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<Character> findByName (String name){
        return characterRepository.findByName(name);
    }
    public Iterable<Character> findByGender(String gender) {
        return characterRepository.findByGender(gender);
    }
    public Iterable<Character> findByRace(String race) {
        return characterRepository.findByRace(race);
    }
    public void  update (Character character){
        characterRepository.save(character);
    }
    public void  deleteById (Integer ID){
        characterRepository.deleteById(ID);
    }
}
