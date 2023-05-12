package com.net.assign5backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.net.assign5backend.entitity.UserEntity;
import com.net.assign5backend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        if(user == null){
            System.out.println("User not found");
            return null;
        }
        return  user;
    }

    public void saveUser(UserEntity user){
        userRepository.save(user);
    }

    public UserEntity getUser(String email){
        return userRepository.findByEmail(email);
    }

    public void deleteUser(String email){
        userRepository.deleteByEmail(email);
    }

    public List<UserEntity> getByRole(String role){
        return userRepository.findByRole(role);
    }

    public UserEntity getUser(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }
}
