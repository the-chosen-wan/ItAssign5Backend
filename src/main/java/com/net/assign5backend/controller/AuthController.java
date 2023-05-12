package com.net.assign5backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.net.assign5backend.entitity.BlogEntity;
import com.net.assign5backend.entitity.MessageEntity;
import com.net.assign5backend.entitity.UserEntity;
import com.net.assign5backend.service.BlogService;
import com.net.assign5backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@EnableScheduling
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Scheduled(fixedDelay=100000)
    public void refresh(){
        blogService.load();
    }

    @PostMapping("/register")
    public ResponseEntity<MessageEntity> register(@RequestBody UserEntity user){
        UserEntity dbUser = userService.loadUserByUsername(user.getEmail());

        if(dbUser==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            return ResponseEntity.ok(new MessageEntity("User registered successfully"));
        }


        else{

            if(passwordEncoder.matches(user.getPassword(), dbUser.getPassword())){
                return ResponseEntity.ok(new MessageEntity("Password correct"));
            }

            else{
                return new ResponseEntity<>(new MessageEntity("Password incorrect"), HttpStatus.CONFLICT);
            }
        }
    }

    @PostMapping("/blog/{id}")
    public ResponseEntity<BlogEntity> getBlog(@PathVariable String id){
        int idInt = Integer.parseInt(id);
        BlogEntity blog = blogService.getBlog(idInt);
        return ResponseEntity.ok(blog);
    }

    @PostMapping("/getBlogs")
    public ResponseEntity<List<BlogEntity>> getBlogs(){
        return ResponseEntity.ok(blogService.getBlogs());
    }

    @PostMapping("/findByTags")
    public ResponseEntity<List<BlogEntity>> findByTag(@RequestBody MessageEntity tag){
        return ResponseEntity.ok(blogService.findByTags(tag.getMessage()));
    }

}
