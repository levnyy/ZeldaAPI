package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.CreaturesService;
import com.zelda.ZeldaAPI.model.Location;
import com.zelda.ZeldaAPI.model.Creatures;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
@RequestMapping("/creatures")
@RestController
public class CreaturesController {
    private final CreaturesService creaturesService;

    public CreaturesController(CreaturesService creaturesService) {
        this.creaturesService = creaturesService;
    }

    @GetMapping(path = "{id}")
    public Creatures findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return creaturesService.findById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
        }
    }

    @GetMapping
    public Iterable<Creatures> findByName(@RequestParam String name) {
        try {
            if (!name.isEmpty())
                return creaturesService.findByName(name);
            else {
                return creaturesService.findAll();
            }
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }
    @GetMapping
    public Iterable<Creatures> findByStrength(Integer strength) {
        try {
            return creaturesService.findByStrength(strength);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Strenght not found");
        }
    }

    @GetMapping
    public Iterable<Creatures> findByDurability(Integer durability) {
        try {
            return creaturesService.findByDurability(durability);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Durability not found");
        }
    }

    @GetMapping
    public Creatures findByLocation(Location location) {
        try {
            creaturesService.findByLocation(location);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
        return findByLocation(location);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Creatures creatures) {
        try {
            creaturesService.insert(creatures);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be inserted");
        }
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Creatures creatures) {
        try {
            creaturesService.update(creatures);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update cannot be completed");
        }
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable(value = "id", required = true) Integer  id) {
        try {
            creaturesService.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}
