package com.projectmanagement.services;

import com.projectmanagement.domain.User;
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
            //Username has to be unique
            //
            return userRepository.save(newUser);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
