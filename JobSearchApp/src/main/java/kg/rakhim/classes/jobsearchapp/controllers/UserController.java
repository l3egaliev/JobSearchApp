package kg.rakhim.classes.jobsearchapp.controllers;


import kg.rakhim.classes.jobsearchapp.entities.User;
import kg.rakhim.classes.jobsearchapp.exceptions.UserNotFoundException;
import kg.rakhim.classes.jobsearchapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        if(userService.getUser(id) == null){
            throw new UserNotFoundException("Пользователь с таким id не найден!");
        }
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<UserNotFoundException> handler(UserNotFoundException ex){
        UserNotFoundException us = new UserNotFoundException(ex.getMessage());
        return new ResponseEntity<>(us, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
