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

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Before
    public void setUp() {
        // Reset cache before each test
        userService.evictCache();
    }

    @Test
    public void saveUser() {
        User testUser = new User("testUser", "testUser", Arrays.asList());
        userService.saveUser(testUser);

        User result = userService.getUserById(testUser.getId());

        Assert.assertEquals("Failure - Couldn't find testUser", testUser, result);
    }

    @Test
    public void updateUser() {
        User testUser = new User("testUser", "testUser", Arrays.asList());
        userService.saveUser(testUser);

        User testUserToUpdate = userService.getUserByUsername("testUser");
        testUserToUpdate.setUsername("testUserUpdated");

        userService.updateUser(testUserToUpdate);

        User result = userService.getUserById(testUserToUpdate.getId());

        Assert.assertEquals("Failure - Couldn't find the testUser or name has not been updated", testUserToUpdate, result);
    }

    @Test
    public void updateUserAddMovie() {
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

        Collection<User> listAfter = userService.getAllUsers();

        int countListAfter = listAfter.size();

        int result = countListAfter - countListBefore;

        Assert.assertNotNull("Failure - expected not null", list);
        Assert.assertEquals("Failure - expected size", 1, result);
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void getMoviesByUserId() {
    }

    @Test
    public void deleteUser() {
    }
}