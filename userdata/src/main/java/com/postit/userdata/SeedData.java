package com.postit.userdata;

import com.github.javafaker.Faker;
import com.postit.userdata.models.*;
import com.postit.userdata.services.PostsService;
import com.postit.userdata.services.RoleService;
import com.postit.userdata.services.SubredditService;
import com.postit.userdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PostsService postsService;

    @Autowired
    private SubredditService subredditService;

    @Autowired
    private RoleService roleService;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(new Locale("en-US"));

        Role user = new Role("User");
        roleService.save(user);

        Posts p2 =  new Posts(faker.backToTheFuture().date(), faker.rickAndMorty().quote());
        Subreddit s2 =  new Subreddit(faker.lordOfTheRings().location(), faker.chuckNorris().fact());
        postsService.save(p2);
        subredditService.save(s2);
        User fuser =  new User("username", "password");
        fuser.setAvatar(faker.internet().avatar());
        s2.getSavedposts().add(new SavedPosts(s2,p2));
        fuser.getUsersubs().add(new UserSubs(fuser,s2 ));
        fuser.getUserroles().add(new UserRoles(fuser, user));

        userService.save(fuser);

        for (int i = 0; i < 20; i++) {
           Posts p1 =  new Posts(faker.backToTheFuture().date(), faker.backToTheFuture().quote());
           Subreddit s1 =  new Subreddit(faker.lordOfTheRings().location(), faker.chuckNorris().fact());
           postsService.save(p1);
           subredditService.save(s1);
           User fakeUser =  new User(faker.pokemon().name(), "password");
           fakeUser.setAvatar(faker.internet().avatar());
           s1.getSavedposts().add(new SavedPosts(s1,p1));
           fakeUser.getUsersubs().add(new UserSubs(fakeUser,s1 ));
           fakeUser.getUserroles().add(new UserRoles(fakeUser, user));

           userService.save(fakeUser);
        }

    }
}
