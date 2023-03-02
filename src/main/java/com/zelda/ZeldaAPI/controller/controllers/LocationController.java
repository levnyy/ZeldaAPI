package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.LocationService;
import com.zelda.ZeldaAPI.model.Location;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Find location by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved location"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @GetMapping(path = "{id}")
    public Location findById(
            @PathVariable(value = "id", required = true) Integer id) {
        try {
            return locationService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }

    @Operation(description = "Find locations by name")
        @ApiResponses(value = {
            @ApiResponse(responseCode= "200", description = "Successfully retrieved list of locations"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @GetMapping("/locations")
    public Iterable<Location> findByName(
            @RequestParam(required = false) String name) {
        try {
            if (!name.isEmpty()) {
                return locationService.findByName(name);
            } else {
                return locationService.findAll();
            }
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }

    @Operation(summary = "Find locations by reward")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of locations"),
            @ApiResponse(responseCode = "404", description = "Reward not found")
    })
    @GetMapping
    public Iterable<Location> findByReward(
            @RequestParam String reward) {
        try {
            return locationService.findByReward(reward);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reward not found");
        }
    }

    @Operation(summary = "Insert a new location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location successfully inserted"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Location cannot be inserted due to an error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(
            @RequestBody @Valid Location location) {
        try {
            locationService.insert(location);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location cannot be inserted");
        }
    }

    @Operation(summary = "Update an existing location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @RequestBody @Valid Location location) {
        try {
            locationService.update(location);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }

    @Operation(summary = "Delete a location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Location successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(
            @PathVariable(value = "id", required = true) Integer id) {
        try {
            locationService.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }
}
