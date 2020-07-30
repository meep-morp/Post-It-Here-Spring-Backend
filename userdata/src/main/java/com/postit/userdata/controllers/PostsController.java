package com.postit.userdata.controllers;

import com.postit.userdata.models.Posts;
import com.postit.userdata.models.PostsRec;
import com.postit.userdata.models.Recommendations;
import com.postit.userdata.models.SavedPosts;
import com.postit.userdata.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @PostMapping(value = "/predict/{numResults}", consumes = "application/json")
    public ResponseEntity<?> getRecommendations(@RequestBody Posts posts, @PathVariable long numResults) {
        Recommendations rec = postsService.findSubreddits(numResults, posts);
        return new ResponseEntity<>(rec, HttpStatus.OK);
    }

    @GetMapping(value = "/searchPosts/{search}", produces = "application/json")
    public ResponseEntity<?> searchPosts(@PathVariable String search) {
        List<Posts> p = postsService.search(search);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
