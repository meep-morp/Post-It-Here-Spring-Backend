package com.postit.userdata.controllers;

import com.postit.userdata.models.User;
import com.postit.userdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PreAuthorize("permitAll()")
    @PostMapping(value = "/signup", consumes = "application/json")
    @Transactional
    public ResponseEntity<?> postNewUser(@Valid @RequestBody User newuser) {
        newuser.setUserid(0);
        newuser = userService.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/myinfo", produces = "application/json")
    public ResponseEntity<?> getMyInfo() {
        User user = userService.getCurrentUserInfo();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
