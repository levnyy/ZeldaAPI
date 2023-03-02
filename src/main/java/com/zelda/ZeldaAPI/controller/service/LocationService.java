package com.zelda.ZeldaAPI.controller.service;


import com.zelda.ZeldaAPI.controller.repository.LocationRepository;
import com.zelda.ZeldaAPI.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
@Service
public class LocationService {
private final LocationRepository locationRepository;
@Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public Iterable<Location> findAll() {
        return locationRepository.findAll();
    }
    public Location findById(Integer id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<Location> findByName (String name){
        return locationRepository.findByName(name);
    }
    public Iterable<Location> findByReward(String reward) {
        return locationRepository.findByRewards(reward);

    }
    public void insert (Location location){
        locationRepository.save(location);
    }
    public void  update (Location location){
        locationRepository.save(location);
    }
    public void  deleteById (Integer id){
        locationRepository.deleteById(id);
    }
}