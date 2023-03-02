package com.zelda.ZeldaAPI.controller.repository;

import com.zelda.ZeldaAPI.model.Character;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Integer> {
    @Query("SELECT c FROM Character c WHERE c.name LIKE CONCAT('%', :name, '%')")
    Iterable<Character> findByName(@Param("name") String name);

    @Query("SELECT c FROM Character c WHERE c.name LIKE CONCAT('%', :gender, '%')")
    Iterable<Character> findByGender(@Param("gender") String gender);

    @Query("SELECT c FROM Character c WHERE c.name LIKE CONCAT('%', :race, '%')")
    Iterable<Character> findByRace(@Param("race") String race);
}
