package com.net.assign5backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.net.assign5backend.service.BlogService;

@RequestMapping("/api/admin")
@RestController
public class AdminController {

    @Autowired
    private BlogService blogService;
    
    @PostMapping("/valid")
    public ResponseEntity<String> valid(){
        return ResponseEntity.ok("Hello Admin");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable String id){
        int idInt = Integer.parseInt(id);
        blogService.delete(idInt);
        return ResponseEntity.ok("Blog deleted");
    }
}
