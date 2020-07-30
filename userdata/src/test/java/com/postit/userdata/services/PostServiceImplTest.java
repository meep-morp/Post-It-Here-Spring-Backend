package com.postit.userdata.services;

import com.postit.userdata.UserdataApplication;
import com.postit.userdata.models.Posts;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
public class PostServiceImplTest {

    @Autowired
    private PostsService postsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        List<Posts> posts = postsService.findAll();

        posts.forEach(post -> System.out.format("%s: %s \n", post.getPostid(), post.getTitle()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void a_findAll() {
        assertEquals(22, postsService.findAll().size());
    }

    @Test
    public void b_save() {
        Posts newPost = new Posts("test", "test");
        postsService.save(newPost);

        assertEquals(23, postsService.findAll().size());
    }

    @Test
    public void c_search() {
        assertEquals(2, postsService.search("test").size());
    }
}