package com.example.user.service;

import com.example.user.DTOs.UserRequestDTO;
import com.example.user.domain.User;
import com.example.user.producers.UserProducer;
import com.example.user.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProducer userProducer;

    @Transactional
    public User saveUser (@Valid UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        User saved = userRepository.save(user);
        userProducer.publishMessageEmail(saved);
        return saved;

    }
}
