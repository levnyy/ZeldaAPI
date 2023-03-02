package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.BossesService;
import com.zelda.ZeldaAPI.model.Bosses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

public class BossesController {
    private final BossesService bossesService;

    public BossesController(BossesService bossesService) {
        this.bossesService = bossesService;
    }

    @GetMapping(path = "{id}")
    public Bosses findById(@PathVariable(value = "id", required = true) Integer ID) {
        try {
            return bossesService.findById(ID);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
    }
@PutMapping
    public Iterable<Bosses> findByName(String name) {
        try {
            if (!name.isEmpty())
            return  bossesService.findByName(name);
            else {
                return bossesService.findAll();
            }
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boss not found");
        }
    }
    @PutMapping
    public Iterable<Bosses> findByHealth(Integer health) {
        try {
            return  bossesService.findByHealth(health);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boss not found");
        }
    }
    @PutMapping
    public Iterable<Bosses> findByWeakness(Integer weakness) {
        try {
            return bossesService.findByWeakness(weakness);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boss not found");
        }
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Bosses bosses) {
        try {
            bossesService.update(bosses);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update cannot be completed");
        }
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable(value = "id", required = true) Integer ID) {
        try {
            bossesService.deleteById(ID);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}