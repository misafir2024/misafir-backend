package com.misafir.services;

import com.misafir.dto.DtoRegister;
import com.misafir.dto.LoginDto;
import com.misafir.entities.User;
import com.misafir.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(DtoRegister dtoRegister) {
        User user = new User();
        user.setUsername(dtoRegister.getUsername());
        user.setEmail(dtoRegister.getEmail());
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        userRepository.save(user);
    }

    public boolean authenticate(LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        // Check if the password matches
        return passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
    }
}
