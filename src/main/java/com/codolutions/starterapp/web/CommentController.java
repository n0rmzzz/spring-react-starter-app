package com.codolutions.starterapp.web;

import java.util.List;

import com.codolutions.starterapp.domain.Comment;
import com.codolutions.starterapp.service.DefaultCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/comments")
public class CommentController {

    private DefaultCommentService service;

    @Autowired
    public CommentController(DefaultCommentService service) {
        this.service = service;
    }

    @RequestMapping (method = RequestMethod.GET)
    public List<Comment> getComments() {
        return service.getComments();
    }

    @RequestMapping (method = RequestMethod.POST)
    public List<Comment> addComment(Comment comment) {
        return service.addComment(comment);
    }

}
