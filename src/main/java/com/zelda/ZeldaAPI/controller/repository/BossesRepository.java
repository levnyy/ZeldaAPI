package com.zelda.ZeldaAPI.controller.repository;

import com.zelda.ZeldaAPI.model.Bosses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BossesRepository extends CrudRepository<Bosses, Integer> {
    @Query("SELECT b FROM Bosses b WHERE b.name LIKE CONCAT('%', :name, '%')")
    Iterable<Bosses> findByName(@Param("name") String name);

    @Query("SELECT b FROM Bosses b WHERE b.name LIKE CONCAT('%', :health, '%')")
    Iterable<Bosses> findByHealth(@Param("health") Integer health);

    @Query("SELECT b FROM Bosses b WHERE b.name LIKE CONCAT('%', :weakness, '%')")
    Iterable<Bosses> findByWeakness(@Param("weakness") Integer weakness);
}
