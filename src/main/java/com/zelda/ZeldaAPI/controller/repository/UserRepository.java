package com.zelda.ZeldaAPI.controller.repository;

import com.zelda.ZeldaAPI.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(@Param("username")String username);
}
