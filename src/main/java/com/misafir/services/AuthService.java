package com.misafir.services;

import com.misafir.dto.DtoRegister;
import com.misafir.entities.User;
import com.misafir.repositories.UserRepository;
import com.misafir.services.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String email) {
        return customUserDetailsService.loadUserByUsername(email);
    }

    public void register(DtoRegister dtoRegister) {
        User user = new User();
        user.setUsername(dtoRegister.getUsername());
        user.setEmail(dtoRegister.getEmail());

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));

        // Set additional user details as required
        user.setEventType(dtoRegister.getEventType());
        user.setMealType(dtoRegister.getMealType());
        user.setPlace(dtoRegister.getPlace());
        user.setLanguage(dtoRegister.getLanguage());
        user.setAmenities(dtoRegister.getAmenities());

        userRepository.save(user);
    }
}
