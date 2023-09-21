package com.example.demospring.controller;

import com.example.demospring.model.User;
import com.example.demospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateUsers(@RequestParam int count) {
        byte[] usersInJson = userService.generateUsersAsJson(count);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "users.json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(usersInJson);
    }

    @PostMapping("/batch")
    public ResponseEntity<?> uploadUsers(@RequestParam MultipartFile file) {
        // Upload and save users to the database
        boolean isUploaded = userService.uploadUsers(file);
        if (isUploaded) {
            return ResponseEntity.ok("Users uploaded successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to upload users");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMyProfile(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = principal.getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(user);
    }
    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserProfile(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

  /*  @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception {
        userDetailsService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }*/
}
