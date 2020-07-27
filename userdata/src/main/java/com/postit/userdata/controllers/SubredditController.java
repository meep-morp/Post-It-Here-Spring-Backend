package com.postit.userdata.controllers;

import com.postit.userdata.services.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subs")
public class SubredditController {

    @Autowired
    private SubredditService subredditService;

}
