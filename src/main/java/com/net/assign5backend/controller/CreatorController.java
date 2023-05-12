package com.net.assign5backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.net.assign5backend.entitity.BlogEntity;
import com.net.assign5backend.entitity.UserEntity;
import com.net.assign5backend.service.BlogService;

@RestController
@RequestMapping("/api/creator")
public class CreatorController {

    @Autowired
    private BlogService blogService;
    
    @PostMapping("/valid")
    public ResponseEntity<String> validateCreator(@RequestBody UserEntity user){
        return ResponseEntity.ok("Creator validated");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBlog(@RequestBody BlogEntity blog){
        blogService.save(blog);
        return ResponseEntity.ok("Blog created");
    }

    @PostMapping("/blog/{id}")
    public ResponseEntity<BlogEntity> getBlog(@PathVariable String id){
        int idInt = Integer.parseInt(id);
        BlogEntity blog = blogService.getBlog(idInt);
        return ResponseEntity.ok(blog);
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editBlog(@RequestBody BlogEntity blog){
        blogService.update(blog);
        return ResponseEntity.ok("Blog edited");
    }
}
