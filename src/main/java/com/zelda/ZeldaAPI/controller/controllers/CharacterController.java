package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.CharacterService;
import com.zelda.ZeldaAPI.model.Character;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
@RequestMapping("/characters")
@RestController
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping(path = "{id}")
    public Character findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return characterService.findById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender not found");
        }
    }
    @GetMapping
    public Iterable<Character> findByRace(String race) {
        try {
            return characterService.findByRace(race);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Race not found");
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Character character) {
        try {
            characterService.insert(character);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be inserted");
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
    public void delete(@PathVariable(value = "id", required = true) Integer id) {
        try {
            characterService.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}
