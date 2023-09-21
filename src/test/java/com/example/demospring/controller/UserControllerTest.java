package com.example.demospring.controller;

import com.example.demospring.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setUsername("dorothea__kovacek");
        testUser.setEmail("test@email.com");
        testUser.setPassword("xoesozma");
        testUser.setCity("East Ronnieville");
        testUser.setAvatar("https://s3.amazonaws.com/uifaces/faces/twitter/gavr1l0/128.jpg");
        testUser.setCompany("Cassin, Abbott and Ankunding");
    }

    @Test
    public void testGetMyProfile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/me")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb3JvdGhlYV9fa292YWNlayIsImlhdCI6MTY5NTI5NTgxMSwiZXhwIjoxNjk4ODk1ODExfQ.JJgKbobxkMFDmONc7KrEYbDIBt8qy_9FbaVqPB2epajfIHjsls_6jvSFGlppS0Djr0uu-Tgxtu4Mzqhp8QgDDA"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserProfile() throws Exception {
        // Generate or mock a valid JWT token
        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb3JvdGhlYV9fa292YWNlayIsImlhdCI6MTY5NTI5NTgxMSwiZXhwIjoxNjk4ODk1ODExfQ.JJgKbobxkMFDmONc7KrEYbDIBt8qy_9FbaVqPB2epajfIHjsls_6jvSFGlppS0Djr0uu-Tgxtu4Mzqhp8QgDDA"; // Replace with actual token generation or mock

        // Print out the token for debugging
        System.out.println("Using token: " + jwtToken);

        // Make a request with the token
        mockMvc.perform(get("/api/users/me")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk());
    }
}
