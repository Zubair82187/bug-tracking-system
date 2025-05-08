package com.bug_tracker.controller;

import com.bug_tracker.model.UserModel;
import com.bug_tracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    //crate a user
    @PostMapping("/")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    //get a user using user id
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> user(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.user(id));
    }

    //get all the users
    @GetMapping("/")
    public ResponseEntity<List<UserModel>> users(){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.users());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

}
