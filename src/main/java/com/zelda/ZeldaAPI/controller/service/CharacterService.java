package com.zelda.ZeldaAPI.controller.service;

import com.zelda.ZeldaAPI.controller.repository.CharacterRepository;
import com.zelda.ZeldaAPI.model.Characters;
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
    public Iterable<Characters> findAll() {
        return characterRepository.findAll();
    }
    public Characters findById(Integer id) {
        Optional<Characters> character = characterRepository.findById(id);
        return character.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<Characters> findByName (String name){
        return characterRepository.findByName(name);
    }
    public Iterable<Characters> findByGender(String gender) {
        return characterRepository.findByGender(gender);
    }
    public Iterable<Characters> findByRace(String race) {
        return characterRepository.findByRace(race);
    }
    public void insert (Characters characters){
        characterRepository.save(characters);
    }
    public void  update (Characters characters){
        characterRepository.save(characters);
    }
    public void  deleteById (Integer id){
        characterRepository.deleteById(id);
    }
}
