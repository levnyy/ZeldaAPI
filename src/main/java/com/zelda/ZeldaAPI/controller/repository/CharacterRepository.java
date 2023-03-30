package com.zelda.ZeldaAPI.controller.repository;

import com.zelda.ZeldaAPI.model.Characters;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Characters, Integer> {
    @Query("SELECT c FROM Characters c WHERE c.name LIKE CONCAT('%', :name, '%')")
    Iterable<Characters> findByName(@Param("name") String name);

    @Query("SELECT c FROM Characters c WHERE c.name LIKE CONCAT('%', :gender, '%')")
    Iterable<Characters> findByGender(@Param("gender") String gender);

    @Query("SELECT c FROM Characters c WHERE c.name LIKE CONCAT('%', :race, '%')")
    Iterable<Characters> findByRace(@Param("race") String race);
}
