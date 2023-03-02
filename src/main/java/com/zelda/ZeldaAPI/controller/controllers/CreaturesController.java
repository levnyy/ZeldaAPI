package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.CreaturesService;
import com.zelda.ZeldaAPI.model.Location;
import com.zelda.ZeldaAPI.model.Creatures;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find creature by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved creature"),
            @ApiResponse(responseCode = "404", description = "Creature not found")
    })
    @GetMapping(path = "{id}")
    public Creatures findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return creaturesService.findById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
        }
    }

    @Operation(summary = "Find creatures by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved creatures"),
            @ApiResponse(responseCode = "404", description = "Creatures not found")
    })
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
    @Operation(summary = "Find creatures by strength")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved creatures"),
            @ApiResponse(responseCode = "404", description = "Creatures not found")
    })
    @GetMapping
    public Iterable<Creatures> findByStrength(Integer strength) {
        try {
            return creaturesService.findByStrength(strength);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Strenght not found");
        }
    }

    @Operation(summary = "Find creatures by durability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved creatures"),
            @ApiResponse(responseCode = "404", description = "Creatures not found")
    })
    @GetMapping
    public Iterable<Creatures> findByDurability(Integer durability) {
        try {
            return creaturesService.findByDurability(durability);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Durability not found");
        }
    }

    @Operation(summary = "Find creature by location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved creature"),
            @ApiResponse(responseCode = "404", description = "Creature not found")
    })
    @GetMapping
    public Creatures findByLocation(Location location) {
        try {
            creaturesService.findByLocation(location);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
        return findByLocation(location);
    }
    @Operation(summary = "Insert a new creature", description = "Inserts a new creature into the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creature successfully inserted"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Insertion failed")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Creatures creatures) {
        try {
            creaturesService.insert(creatures);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be inserted");
        }
    }
    @Operation(summary = "Update an existing creature", description = "Updates an existing creature in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creature successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Creature not found")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Creatures creatures) {
        try {
            creaturesService.update(creatures);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update cannot be completed");
        }
    }
    @Operation(summary = "Delete creature by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully deleted creature"),
            @ApiResponse(responseCode = "404", description = "Creature not found")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable(value = "id", required = true) Integer  id) {
        try {
            creaturesService.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}
