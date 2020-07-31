package com.postit.userdata.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postit.userdata.UserdataApplication;
import com.postit.userdata.models.*;
import com.postit.userdata.services.SubredditService;
import com.postit.userdata.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserdataApplication.class)
@WithMockUser(username = "admin", roles = {"ADMIN", "DATA"})
public class SubredditControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SubredditService subredditService;
    List<User> subList = new ArrayList<>();

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        Subreddit s1 = new Subreddit("Title", "Desc");
        s1.setSubid(1);
        User u1 = new User("username", "password");
        u1.setUserid(1);
        u1.getUsersubs().add(new UserSubs(u1,s1));

        subList.add(u1);
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveSubreddit() throws Exception{
        String apiUrl = "/subs/save";
        ObjectMapper mapper = new ObjectMapper();
        Subreddit s1 = new Subreddit("Titleee", "Desc");
        s1.setSubid(1);
        String subString = mapper.writeValueAsString(s1);

        Mockito.when(subredditService.save(any(Subreddit.class))).thenReturn(s1);
        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(subString);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteSub() throws Exception{
        String apiUrl = "/subs/sub/1";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void subscribeToSubPosts() throws Exception{
        String apiUrl = "/subs/getposts/awww";
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = (MvcResult) mockMvc.perform(rb).andExpect(status().isOk());
    }

    @Test
    public void savePost() throws Exception{
        String apiUrl = "/savepost/awww";
        Posts p1 = new Posts("Post Title", "Selftext");
        User b3 = new User("meep-morp", "password");
        Subreddit s1 = new Subreddit("awww", "Desc");
        b3.getUsersubs().add(new UserSubs(b3, s1));
        s1.getSavedposts().add(new SavedPosts(s1, p1));

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(s1);

        Mockito.when(subredditService.save(any(Subreddit.class))).thenReturn(s1);
        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(userString);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}