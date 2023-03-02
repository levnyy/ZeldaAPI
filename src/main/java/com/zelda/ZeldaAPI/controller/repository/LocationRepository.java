package com.zelda.ZeldaAPI.controller.repository;

import com.zelda.ZeldaAPI.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    @Query("SELECT l FROM Location l WHERE l.name LIKE CONCAT('%', :name, '%')")
    Iterable<Location> findByName(@Param("name") String name);
    @Query("SELECT l FROM Location l WHERE l.name LIKE CONCAT('%', :reward, '%')")
    Iterable<Location> findByRewards(@Param("reward") String reward);
}
