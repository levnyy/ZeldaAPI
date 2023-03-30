package com.zelda.ZeldaAPI.controller.service;
import com.zelda.ZeldaAPI.controller.repository.UserRepository;
import com.zelda.ZeldaAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(EntityNotFoundException::new);
    }
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userRepository.findByUsername(username);
    }
    public User signUp(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public void update (User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void deleteById (Integer id){
        userRepository.deleteById(id);
    }
}