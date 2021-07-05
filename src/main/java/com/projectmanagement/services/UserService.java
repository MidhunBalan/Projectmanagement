package com.projectmanagement.services;

import com.projectmanagement.domain.User;
import com.projectmanagement.exceptions.UsernameAlreadyExistsException;
import com.projectmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser){
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            //Username has to be unique
            // make sure that the password and confirmpassword are same
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        }catch(Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }
    }
}
