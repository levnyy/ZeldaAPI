package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.LocationsService;
import com.zelda.ZeldaAPI.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

public class LocationsController {
    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }
    @GetMapping(path = "{id}")
    public Location findById(@PathVariable(value = "id", required = true) Integer ID) {
        try {
            return locationsService.findById(ID);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
    }
    @GetMapping
    public Location findByName(String name) {
        try {
            if (!name.isEmpty())
                return locationsService.findByName(name);
            else {
                return locationsService.findAll();
            }
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }
    @GetMapping
    public Location findByRewards(String reward) {
        try {
            return locationsService.findByRace(reward);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Location location) {
        try {
            locationsService.update(location);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update cannot be completed");
        }
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable(value = "id", required = true) Integer  ID) {
        try {
            locationsService.deleteById(ID);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}
