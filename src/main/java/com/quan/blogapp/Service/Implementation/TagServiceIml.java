package com.quan.blogapp.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.blogapp.Entity.Tag;
import com.quan.blogapp.Repository.TagRepos;
import com.quan.blogapp.Service.TagService;

@Service
public class TagServiceIml implements TagService {
    @Autowired
    TagRepos tagRepos;

    @Override
    public List<Tag> getTags() {
        return tagRepos.findAll();
    }

}
