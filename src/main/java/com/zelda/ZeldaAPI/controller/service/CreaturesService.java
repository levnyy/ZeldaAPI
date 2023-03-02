package com.zelda.ZeldaAPI.controller.service;

import com.zelda.ZeldaAPI.controller.repository.CreaturesRepository;
import com.zelda.ZeldaAPI.model.Creatures;
import com.zelda.ZeldaAPI.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
@Service
public class CreaturesService {
    private final CreaturesRepository creaturesRepository;
@Autowired
    public CreaturesService(CreaturesRepository creaturesRepository) {
        this.creaturesRepository = creaturesRepository;
    }
    public Iterable<Creatures> findAll() {
        return creaturesRepository.findAll();
    }
    public Creatures findById(Integer id) {
        Optional<Creatures> creatures = creaturesRepository.findById(id);
        return creatures.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<Creatures> findByName (String name){
        return creaturesRepository.findByName(name);
    }
    public Iterable<Creatures> findByStrength(Integer strength) {
        return creaturesRepository.findByStrength(strength);
    }
    public Iterable<Creatures> findByDurability(Integer durability) {
        return creaturesRepository.findByDurability(durability);
    }
    public Creatures findByLocation(Location location) {
        return (Creatures) creaturesRepository.findByLocation(location);
    }
    public void insert (Creatures creatures){
        creaturesRepository.save(creatures);
    }
    public void  update (Creatures creatures){
        creaturesRepository.save(creatures);
    }
    public void  deleteById (Integer id){
        creaturesRepository.deleteById(id);
    }
}
