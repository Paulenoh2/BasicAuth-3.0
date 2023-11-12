package com.example.demo.service;

import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;



public interface Service {



String createUser(User user);
List<User> getAllUsers();

Optional <User> getUserById(Long id);






}
