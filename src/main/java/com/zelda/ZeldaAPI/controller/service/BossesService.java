package com.zelda.ZeldaAPI.controller.service;

import com.zelda.ZeldaAPI.controller.repository.BossesRepository;
import com.zelda.ZeldaAPI.model.Bosses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class BossesService {
    private final BossesRepository bossesRepository;
    @Autowired
    public BossesService(BossesRepository bossesRepository) {
        this.bossesRepository = bossesRepository;
    }
    public Iterable<Bosses> findAll() {
        return bossesRepository.findAll();
    }
    public Bosses findById(Integer ID) {
        Optional<Bosses> bosses = bossesRepository.findById(ID);
        return bosses.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<Bosses> findByName (String name){
        return bossesRepository.findByName(name);
    }
    public Iterable<Bosses> findByHealth(Integer health) {
        return bossesRepository.findByHealth(health);
    }
    public Iterable<Bosses> findByWeakness (Integer weakness) {
            return bossesRepository.findByWeakness(weakness);
    }
    public void  update (Bosses bosses){
        bossesRepository.save(bosses);
    }
    public void  deleteById (Integer id){
        bossesRepository.deleteById(id);
    }
}
