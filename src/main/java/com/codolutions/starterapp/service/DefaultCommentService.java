package com.codolutions.starterapp.service;

import com.codolutions.starterapp.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCommentService implements CommentService {

    private static final int PAGE_SIZE = 10;

    private final CommentRepository repository;
    private List<Comment> comments = new ArrayList<>();

    @Autowired
    public DefaultCommentService(final CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> getComments() {
        return repository.findAll(new PageRequest(0, PAGE_SIZE)).getContent();
    }

    public List<Comment> addComment(Comment comment) {
        repository.save(comment);
        return getComments();
    }
}
