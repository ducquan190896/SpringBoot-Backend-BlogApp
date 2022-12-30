package com.quan.blogapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.blogapp.Entity.Tag;
import com.quan.blogapp.Service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    
    @Autowired
    TagService tagService;

    @GetMapping("/all")
    public ResponseEntity<List<Tag>> getAll() {
        return new ResponseEntity<List<Tag>>(tagService.getTags(), HttpStatus.OK);
    }
}
