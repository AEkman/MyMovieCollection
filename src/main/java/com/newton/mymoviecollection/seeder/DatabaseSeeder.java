package com.newton.mymoviecollection.seeder;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.entity.User;
import com.newton.mymoviecollection.repository.UserRepository;
import com.newton.mymoviecollection.service.MovieService;
import com.newton.mymoviecollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Override
    public void run(String... strings) throws Exception {

        // Movie 1
        Movie harryPotter = new Movie("Harry Potter and the Deathly Hallows: Part 2",
                "2011",
                "tt1201607",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMjIyZGU4YzUtNDkzYi00ZDRhLTljYzctYTMxMDQ4M2E0Y2YxXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"
        );
        movieService.saveMovie(harryPotter);

        // Movie 2
        Movie terminator = new Movie("Terminator 2",
                "1991",
                "tt0103064",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMGU2NzRmZjUtOGUxYS00ZjdjLWEwZWItY2NlM2JhNjkxNTFmXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"
        );
        movieService.saveMovie(terminator);

        // Movie 3
        Movie dirtyHarry = new Movie("Dirty Harry",
                "1971",
                "tt0066999",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMTg3MDQ4Njg5MV5BMl5BanBnXkFtZTgwMTU2OTM5NDE@._V1._CR96,139,852,1256_SY132_CR0,0,89,132_AL_.jpg_V1_SX300.jpg"
        );
        movieService.saveMovie(dirtyHarry);

        // Movie 4
        Movie transformers = new Movie("Transformers",
                "2007",
                "tt0418279",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNDg1NTU2OWEtM2UzYi00ZWRmLWEwMTktZWNjYWQ1NWM1OThjXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
        );
        movieService.saveMovie(transformers);

        // Create User 1 "user/user"
        User user = new User("user", "user", Arrays.asList());
        userService.saveUser(user);

        // Create User 2 "admin/admin"
        User admin = new User("admin", "admin", Arrays.asList());
        userService.saveUser(admin);

        // Add 2 movies to user user
        userService.updateUserAddMovie(user, harryPotter);
        userService.updateUserAddMovie(user, terminator);

        // Add 3 movies to user admin
        userService.updateUserAddMovie(admin, harryPotter);
        userService.updateUserAddMovie(admin, dirtyHarry);
        }
}