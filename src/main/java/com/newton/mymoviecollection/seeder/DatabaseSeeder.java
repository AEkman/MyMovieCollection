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


        // User 1 "user/user"
        User user = new User("user", "user", Arrays.asList(
                new Movie("Titanic", "1995", "imdbid", "link"),
                new Movie("Batman", "1992", "imdbid", "link")
        ));

        // User 2 "admin/admin"
        User admin = new User("admin", "admin", Arrays.asList());

        userService.saveUser(user);
        userService.saveUser(admin);
        }
}