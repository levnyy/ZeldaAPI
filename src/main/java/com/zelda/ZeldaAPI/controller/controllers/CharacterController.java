package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.CharacterService;
import com.zelda.ZeldaAPI.model.Character;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find character by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved character"),
            @ApiResponse(responseCode = "404", description = "Character not found")
    })
    @GetMapping("/{id}")
    public Character findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return characterService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }

    @Operation(summary = "Find characters by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of characters"),
            @ApiResponse(responseCode = "404", description = "Character name not found")
    })
    @GetMapping
    public Iterable<Character> findByName(String name) {
        try {
            if (!name.isEmpty())
                return characterService.findByName(name);
            else {
                return characterService.findAll();
            }
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character name not found");
        }
    }
    @Operation(summary = "Find characters by gender")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of characters"),
            @ApiResponse(responseCode = "404", description = "Gender not found")
    })
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
    @Operation(summary = "Insert a new Character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character successfully inserted"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Character cannot be inserted due to an error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Character character) {
        try {
            characterService.insert(character);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be inserted");
        }
    }
    @Operation(summary = "Update a existing Character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Character character) {
        try {
            characterService.update(character);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update cannot be completed");
        }
    }
    @Operation(summary = "Delete a Character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Location successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable(value = "id", required = true) Integer id) {
        try {
            characterService.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}
