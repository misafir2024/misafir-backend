package com.misafir.controller;

import com.misafir.dto.DtoRegister;
import com.misafir.dto.LoginDto;
import com.misafir.security.JwtTokenUtil;
import com.misafir.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody DtoRegister dtoRegister) {
        authService.register(dtoRegister);
        return ResponseEntity.status(201).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
        try {
            // This line authenticates the user using Spring Security's AuthenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
        } catch (Exception e) {
            // Return 401 if authentication fails
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        // After successful authentication, load the user and generate JWT
        UserDetails userDetails = authService.loadUserByUsername(loginDto.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);  // Return the JWT token
    }

}
