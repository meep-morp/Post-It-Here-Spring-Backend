package com.postit.userdata.services;

import com.postit.userdata.UserdataApplication;
import com.postit.userdata.models.Subreddit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserdataApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubredditServiceImplTest {

    @Autowired
    private SubredditService subredditService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);

        List<Subreddit> subs = subredditService.findAll();

        subs.forEach(sub -> System.out.format("%s: %s \n", sub.getSubid(), sub.getTitle()));
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void a_findAll() {
        assertEquals(5, subredditService.findAll().size());
    }

    @Test
    public void x_save() {
    }

    @Test
    public void b_findBySubName() {
    }

    @Test
    public void c_findById() {
    }

    @Test
    public void d_delete() {
    }
}