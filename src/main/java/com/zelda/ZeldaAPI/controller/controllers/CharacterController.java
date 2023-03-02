package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.CharacterService;
import com.zelda.ZeldaAPI.model.Bosses;
import com.zelda.ZeldaAPI.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping(path = "{id}")
    public Character findById(@PathVariable(value = "id", required = true) Integer ID) {
        try {
            return characterService.findById(ID);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
    }
    @GetMapping
    public Iterable<Character> findByName(String name) {
        try {
            if (!name.isEmpty())
                return characterService.findByName(name);
            else {
                return characterService.findAll();
            }
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }
    @GetMapping
    public Iterable<Character> findByGender(String gender) {
        try {
            return characterService.findByGender(gender);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }
    @GetMapping
    public Iterable<Character> findByRace(String race) {
        try {
            return characterService.findByRace(race);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Character character) {
        try {
            characterService.update(character);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update cannot be completed");
        }
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable(value = "id", required = true) Integer ID) {
        try {
            characterService.deleteById(ID);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}
