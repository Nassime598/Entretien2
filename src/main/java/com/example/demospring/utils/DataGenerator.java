package com.example.demospring.utils;

import com.example.demospring.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class DataGenerator {

    private final Faker faker = new Faker();

    public User generateRandomUser() {
        String firstName = faker.name().firstName().toLowerCase();
        String lastName = faker.name().lastName().toLowerCase();
        String username = firstName + "__" + lastName;
        System.out.println("username " +username);
        User user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setBirthDate(faker.date().birthday());
        user.setCity(faker.address().city());
        user.setCountry(faker.address().countryCode());
        user.setAvatar(faker.internet().avatar());
        user.setCompany(faker.company().name());
        user.setJobPosition(faker.job().position());
        user.setMobile(faker.phoneNumber().cellPhone());
        user.setUsername(username);
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6, 10));
        System.out.println("password "+faker.internet().password(6, 10));
        user.setRole(faker.random().nextBoolean() ? User.Role.ADMIN : User.Role.USER);
        return user;
    }

    public List<User> generateRandomUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(generateRandomUser());
        }
        return users;
    }


}