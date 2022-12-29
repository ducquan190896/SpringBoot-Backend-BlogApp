package com.quan.blogapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Tag;

@Repository
public interface TagRepos extends JpaRepository<Tag, Long> {
    

    @Query(
        value = "select * from tag where content = ?",
        nativeQuery = true
    )
    Optional<Tag> findByContent(String content);
}
