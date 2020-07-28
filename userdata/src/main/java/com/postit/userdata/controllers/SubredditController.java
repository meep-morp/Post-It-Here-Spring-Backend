package com.postit.userdata.controllers;

import com.postit.userdata.models.RedditApi;
import com.postit.userdata.models.Subreddit;
import com.postit.userdata.services.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/subs")
public class SubredditController {

    @Autowired
    private SubredditService subredditService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<?> saveSubreddit(@Valid @RequestBody Subreddit newSub) {
        newSub.setSubid(0);
        newSub = subredditService.save(newSub);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newSubURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newSub.getSubid())
                .toUri();
        responseHeaders.setLocation(newSubURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/sub/{id}")
    public ResponseEntity<?> deleteSub(@PathVariable long id) {
        subredditService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getposts/{subname}", produces = "application/json")
    public ResponseEntity<?> subscribeToSubPosts(@PathVariable String subname) {
        RedditApi newPosts = subredditService.getPosts(subname);
        return new ResponseEntity<>(newPosts, HttpStatus.OK);
    }
}
