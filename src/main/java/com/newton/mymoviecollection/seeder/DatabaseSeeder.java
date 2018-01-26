package com.newton.mymoviecollection.seeder;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.entity.Role;
import com.newton.mymoviecollection.entity.User;
import com.newton.mymoviecollection.repository.RatingRepository;
import com.newton.mymoviecollection.repository.RoleRepository;
import com.newton.mymoviecollection.repository.UserRepository;
import com.newton.mymoviecollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public DatabaseSeeder(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {

//        User admin = new User("user", "user", new Role("ADMIN"));
//        userService.save(admin);

//        List<User> users = new ArrayList<>();
//        users.add(new User("admin", "admin", new Role("ADMIN")));
//        users.add(new User("user", "user", new Role("USER")));
//
//        userService.save(users);
    }
}