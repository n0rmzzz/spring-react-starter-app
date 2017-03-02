package com.codolutions.starterapp.service;

import com.codolutions.starterapp.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();

    List<Comment> addComment(Comment comment);
}
