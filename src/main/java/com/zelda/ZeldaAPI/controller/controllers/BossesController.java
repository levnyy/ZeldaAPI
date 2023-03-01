package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.BossesService;
import com.zelda.ZeldaAPI.model.Bosses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

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

}