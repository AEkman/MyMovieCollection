package com.newton.mymoviecollection.service;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    private User testUser;

    private User createdUserId;

    @Before
    public void setUp() {
        // Reset cache before each test
        userService.evictCache();

        // Setup a new user for testing
        testUser = new User("testUser", "testUser", Arrays.asList());
        userService.saveUser(testUser);

        // Get info "id" for testUser
        createdUserId = userService.getUserByUsername("testUser");
    }

    @Test
    public void saveUser() {
        User result = userService.getUserById(createdUserId.getId());

        Assert.assertEquals("Failure - Couldn't find testUser", "testUser", result.getUsername());
    }

    @Test
    public void updateUser() {
        createdUserId.setUsername("testUserUpdated");
        userService.updateUser(createdUserId);

        User result = userService.getUserById(createdUserId.getId());

        Assert.assertEquals("Failure - Couldn't find the testUser or name has not been updated", createdUserId, result);
    }

    @Test
    public void updateUserAddMovie() {
//        Movie testMovie = new Movie("testTitle", "TestYear", "imdbid1234", "testPoster");
//
//        userService.updateUserAddMovie(testUser, testMovie);
//
//        List<Movie> result = userService.getMoviesByUserId(createdUserId.getId());
//
//        Assert.assertEquals("Failed - couldnt't find new movie", "testTitle", result);
    }

    @Test
    public void updateUserDeleteMovie() {
    }

    @Test
    public void getAllUsers() {

        Collection<User> list = userService.getAllUsers();
        int countListBefore = list.size();

        User testUser = new User("testuser", "testpassword", Arrays.asList());
        userService.saveUser(testUser);

        List<User> listAfter = userService.getAllUsers();

        int countListAfter = listAfter.size();

        int result = countListAfter - countListBefore;

        Assert.assertNotNull("Failure - expected not null", list);
        Assert.assertEquals("Failure - expected size", 1, result);
    }

    @Test
    public void getUserById() {
        User result = userService.getUserById(createdUserId.getId());

        Assert.assertEquals("Failure - get user by id failed", createdUserId.getId(), result.getId());
    }

    @Test
    public void getMoviesByUserId() {

    }

    @Test
    public void deleteUser() {
        userService.deleteUser(createdUserId.getId());

        User result = userService.getUserById(createdUserId.getId());

        Assert.assertEquals("Failed - user not deleted", null, result);
    }
}