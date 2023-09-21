package com.example.demospring.service;

import com.example.demospring.config.JwtConfig;
import com.example.demospring.model.JwtResponse;
import com.example.demospring.model.User;
import com.example.demospring.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtConfig jwtConfig;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateUser() {
        // Mocking a user from the repository
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("testPassword", mockUser.getPassword())).thenReturn(true);

        // Mocking the Authentication object and its methods
        UserDetails mockUserDetails = mock(UserDetails.class);
        when(mockUserDetails.getUsername()).thenReturn("testUser");
        when(authentication.getPrincipal()).thenReturn(mockUserDetails);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);


        // Mocking retrieval of secret key from JwtConfig
        String mockSecretKey = "mockBase64Secret";
        when(jwtConfig.getSecret()).thenReturn(mockSecretKey);

        // Call the method from authService to authenticate
        JwtResponse response = authService.authenticate("dorothea__kovacek", "xoesozma");

        assertNotNull(response, "JwtResponse should not be null");
    }
}
