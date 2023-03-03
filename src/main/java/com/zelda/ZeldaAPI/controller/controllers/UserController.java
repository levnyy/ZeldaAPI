package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.UserService;
import com.zelda.ZeldaAPI.model.Bosses;
import com.zelda.ZeldaAPI.model.User;
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
@RequestMapping("/users")
@RestController
public class UserController {
    private UserService userService;

    public Iterable<User> findAll() {
        return userService.findAll();
    }
@Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public User findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return userService.findById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
    }
@Operation(summary = "Sign Up a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public User signUp(User user) {
        try {
            return userService.signUp(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict");
        }
    }
    @Operation(summary = "Update an existing User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void  update(@RequestBody @Valid User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Update cannot be completed");
        }
    }

    @Operation(summary = "Delete a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User successfully deleted", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bosses.class))}),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void  delete(@PathVariable(value = "id", required = true) Integer id) {
        try {
            userService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}