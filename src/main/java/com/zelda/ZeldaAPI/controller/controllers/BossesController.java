package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.BossesService;
import com.zelda.ZeldaAPI.model.Bosses;
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
@RequestMapping("/bosses")
@RestController
public class BossesController {
    private final BossesService bossesService;

    public BossesController(BossesService bossesService) {
        this.bossesService = bossesService;
    }


@Operation(summary = "Find Bosses by id")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Bosses.class))}),
        @ApiResponse(responseCode = "400",description = "Invalid status value", content = {@Content(mediaType = "application/json")})
})
@GetMapping(path = "{id}")
public Bosses findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return bossesService.findById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
    }

@Operation(summary = "Find Bosses by name")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Bosses.class))}),
        @ApiResponse(responseCode = "400",description = "Invalid status value", content = {@Content(mediaType = "application/json")})
})
@GetMapping
    public Iterable<Bosses>findByName(String name) {
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

@Operation(summary = "Find Bosses by health")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Bosses.class))}),
        @ApiResponse(responseCode = "400",description = "Invalid status value", content = {@Content(mediaType = "application/json")})
})
@GetMapping
    public Iterable<Bosses> findByHealth(Integer health) {
        try {
            return bossesService.findByHealth(health);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Health not found");
        }
    }

@Operation(summary = "Find Bosses by weakness")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Bosses.class))}),
        @ApiResponse(responseCode = "400",description = "Invalid status value", content = {@Content(mediaType = "application/json")})
})
@GetMapping
    public Iterable<Bosses> findByWeakness(Integer weakness) {
        try {
            return bossesService.findByWeakness(weakness);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weakness not found");
        }
    }
@Operation(summary = "Insert a new Boss")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Boss successfully inserted", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Bosses.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid request body"),
        @ApiResponse(responseCode = "404", description = "Character cannot be inserted due to an error")
})
@PostMapping
@ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Bosses bosses) {
        try {
            bossesService.insert(bosses);
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
    public void update(@RequestBody @Valid Bosses bosses) {
        try {
            bossesService.update(bosses);
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
    public void delete(@PathVariable(value = "id", required = true) Integer ID) {
        try {
            bossesService.deleteById(ID);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}