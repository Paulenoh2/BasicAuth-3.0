package com.example.demo.controller;


import com.example.demo.entities.User;
import com.example.demo.service.serviceImple.ServiceImplement;
import com.example.demo.user.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:2400")
public class UserController {


    @Autowired
private ServiceImplement serviceImplement;



    @PostMapping("/authenticate")
    public String authenti (@RequestBody Authen authen){
        return "Login successful";
    }





    @GetMapping("/welcome")
    public String free(){
        return ("welcome to first online project  without Authentication");
    }

@PostMapping("/create")
    public String createNewUser (@RequestBody User user){
        serviceImplement.createUser(user);
        return ("User was successfully create");
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List <User> getAllUsers (){
        final List <User> getAllUsers = serviceImplement.getAllUsers();
        return getAllUsers;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public  Optional<User> getUserById (@PathVariable Long id){
       final   Optional <User> getById = serviceImplement.getUserById(id);
       return getById;
    }






}
