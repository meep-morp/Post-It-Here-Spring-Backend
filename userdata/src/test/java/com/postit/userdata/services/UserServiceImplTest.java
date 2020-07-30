package com.postit.userdata.services;

import com.postit.userdata.UserdataApplication;
import com.postit.userdata.exceptions.ResourceNotFoundException;
import com.postit.userdata.models.Subreddit;
import com.postit.userdata.models.User;
import com.postit.userdata.models.UserSubs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserdataApplication.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    private SubredditService subredditService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        List<User> users = userService.findAll();

        users.forEach(user -> System.out.format("%s: %s \n", user.getUserid(), user.getUsername()));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void b_save() {

        User testUser = new User("test", "test");
        userService.save(testUser);

        assertEquals("test", userService.findByUsername("test").getUsername());

    }

    @Test
    public void a_findAll() {
        assertEquals(21, userService.findAll().size());
    }

    @Test
    public void d_findByUsername() {
        assertEquals(4, userService.findByUsername("username").getUserid());
    }

    @Test
    public void e_findById() {
        assertEquals("username", userService.findById(4).getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void z_delete() {
        userService.delete(7);
        userService.findById(7);
    }
}