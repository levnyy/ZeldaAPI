package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.LocationService;
import com.zelda.ZeldaAPI.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
@RequestMapping("/locations")
@RestController
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @GetMapping(path = "{id}")
    public Location findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return locationService.findById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
    }
    @GetMapping
    public Iterable<Location> findByName(String name) {
        try {
            if (!name.isEmpty())
                return locationService.findByName(name);
            else {
                return locationService.findAll();
            }
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }
    @GetMapping
    public Iterable<Location> findByReward(String reward) {
        try {
            return locationService.findByReward(reward);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reward not found");
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Location location) {
        try {
            locationService.insert(location);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be inserted");
        }
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Location location) {
        try {
            locationService.update(location);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update cannot be completed");
        }
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable(value = "id", required = true) Integer  id) {
        try {
            locationService.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}
