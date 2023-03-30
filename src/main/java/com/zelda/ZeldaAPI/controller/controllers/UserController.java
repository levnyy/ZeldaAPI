package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.UserService;
import com.zelda.ZeldaAPI.model.Bosses;
import com.zelda.ZeldaAPI.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(@RequestBody @Valid UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns every user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Users were found successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "Users could not be found.",
                    content = @Content),
            @ApiResponse(responseCode = "400",description = "Validation failed.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})})

// Hier wird nach allen usern gesucht.
    public Iterable<User> findAll(){
        return userService.findAll();
    }


    @GetMapping(path = "/id")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns user with this id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User was found successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "User could not be found.",
                    content = @Content),
            @ApiResponse(responseCode = "400",description = "Validation failed.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})})

// hier wird für user nach einer id gesucht
    public User findById(@RequestParam(value = "id") Integer id){
        try {
            return userService.findById(id);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping(path = "/name")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns user with this username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User was found successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "User could not be found.",
                    content = @Content),
            @ApiResponse(responseCode = "400",description = "Validation failed.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})})

// hier wird für user nach username gesucht.
    public User findByUsername(@RequestParam(value = "username") String username) {
        try {
            return userService.findByUsername(username);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }


    @PostMapping(value = "/sign-up", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new user and changes mapping to JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User was created successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "User could not be created.",
                    content = @Content),
            @ApiResponse(responseCode = "400",description = "Validation failed.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})})

// Hier kann man einen user anmelden sign up, es hat ein catch für eine Runtimeexception
    public void update(@RequestBody User user){
        try {
            userService.update(user);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict");
        }
    }



    @DeleteMapping(path = "/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deletes a user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User was deleted successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "User could not be deleted.",
                    content = @Content),
            @ApiResponse(responseCode = "400",description = "Validation failed.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})})

// hier kann man einen user entfernen, es hat ein catch für eine Runtimeexception
    public void delete(@PathVariable Integer id){
        try {
            userService.deleteById(id);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
    }

}