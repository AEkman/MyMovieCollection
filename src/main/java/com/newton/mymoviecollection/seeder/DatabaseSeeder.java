package com.newton.mymoviecollection.seeder;

import com.newton.mymoviecollection.entity.User;
import com.newton.mymoviecollection.repository.UserRepository;
import com.newton.mymoviecollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... strings) throws Exception {

        User user = new User("user", "user", Arrays.asList());
    }
}