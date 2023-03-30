package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.CharacterService;
import com.zelda.ZeldaAPI.model.Bosses;
import com.zelda.ZeldaAPI.model.Characters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            @ApiResponse(responseCode = "200", description = "Successfully retrieved character", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "404", description = "Character not found")
    })
    @GetMapping("{id}")
    public Characters findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return characterService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }

    @Operation(summary = "Find characters by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of characters", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "404", description = "Character name not found")
    })
    @GetMapping(params = "name")
    public Iterable<Characters> findByName(String name) {
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
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of characters", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "404", description = "Gender not found")
    })
    @GetMapping(params = "gender")
    public Iterable<Characters> findByGender(String gender) {
        try {
            return characterService.findByGender(gender);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender not found");
        }
    }
    @Operation(summary = "Find characters by race")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of characters", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "404", description = "Race not found")
    })
    @GetMapping(params = "race")
    public Iterable<Characters> findByRace(String race) {
        try {
            return characterService.findByRace(race);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Race not found");
        }
    }
    @Operation(summary = "Insert a new Character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character successfully inserted", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Character cannot be inserted due to an error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Characters characters) {
        try {
            characterService.insert(characters);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be inserted");
        }
    }
    @Operation(summary = "Update a existing Character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location successfully updated", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Characters characters) {
        try {
            characterService.update(characters);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update cannot be completed");
        }
    }
    @Operation(summary = "Delete a Character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Location successfully deleted", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
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
