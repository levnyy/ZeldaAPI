package com.zelda.ZeldaAPI.controller.controllers;

import com.zelda.ZeldaAPI.controller.service.LocationsService;

public class LocationsController {
    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }
}
