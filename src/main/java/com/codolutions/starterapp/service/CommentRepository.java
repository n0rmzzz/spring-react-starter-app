package com.codolutions.starterapp.service;

import com.codolutions.starterapp.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Page<Comment> findAll(Pageable pageable);

    Page<Comment> findByAuthor(String author, Pageable pageable);
}
