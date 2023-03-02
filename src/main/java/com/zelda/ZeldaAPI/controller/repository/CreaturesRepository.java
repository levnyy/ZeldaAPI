package com.zelda.ZeldaAPI.controller.repository;

import com.zelda.ZeldaAPI.model.Creatures;
import com.zelda.ZeldaAPI.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreaturesRepository extends CrudRepository<Creatures, Integer> {
    @Query("SELECT c FROM Creatures c WHERE c.name LIKE CONCAT('%', :name, '%')")
    Iterable<Creatures> findByName(@Param("name") String name);

    @Query("SELECT c FROM Creatures c WHERE c.name LIKE CONCAT('%', :strength, '%')")
    Iterable<Creatures> findByStrength(@Param("strength") Integer strength);

    @Query("SELECT c FROM Creatures c WHERE c.name LIKE CONCAT('%', :durability, '%')")
    Iterable<Creatures> findByDurability(@Param("durability") Integer durability);
    @Query("SELECT c FROM Creatures c JOIN Location l WHERE l.name LIKE CONCAT('%', :location, '%')")
    Iterable<Creatures> findByLocation(@Param("location") Location location);
}
