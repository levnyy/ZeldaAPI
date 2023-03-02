package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.UserService;
import org.apache.catalina.User;
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
    @GetMapping
    public User findById(@PathVariable(value = "id", required = true) Integer id) {
        try {
            return userService.findById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
    }
    @PostMapping(value = "/sign-up", consumes = "application/json")
    public User signUp(User user) {
        try {
            return userService.signUp(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict");
        }
    }
    @PutMapping(consumes = "application/json")
    public void  update(@RequestBody @Valid User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Update cannot be completed");
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void  delete(@PathVariable(value = "id", required = true) Integer id) {
        try {
            userService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item cannot be deleted");
        }
    }
}