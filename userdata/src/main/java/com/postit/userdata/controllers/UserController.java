package com.postit.userdata.controllers;

import com.postit.userdata.models.User;
import com.postit.userdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<?> getAll() {
        List<User> userList = userService.findAll();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
