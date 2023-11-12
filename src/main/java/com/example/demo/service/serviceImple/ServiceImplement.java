package com.example.demo.service.serviceImple;

import com.example.demo.entities.User;
import com.example.demo.entities.UserDetailsImplement;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Service;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImplement implements Service, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional <User> user = userRepository.findByUsername(username);
        return user.map(UserDetailsImplement::new)
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }

    @Override
    public String createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userRepository.save(user);
     return "USER SUCCESSFULLY CREATED";
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> getUserById = userRepository.findById(id);
        return getUserById;
    }

    @Override
    public List < User> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        return allUser;
    }
}
