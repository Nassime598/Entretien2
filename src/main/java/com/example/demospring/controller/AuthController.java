package com.example.demospring.controller;

import com.example.demospring.config.JwtConfig;
import com.example.demospring.config.JwtUtil;
import com.example.demospring.model.JwtRequest;
import com.example.demospring.model.JwtResponse;
import com.example.demospring.service.AuthService;
import com.example.demospring.service.AuthenticationService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtConfig jwtTokenUtil;
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)  {
        final String token = authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }

}



