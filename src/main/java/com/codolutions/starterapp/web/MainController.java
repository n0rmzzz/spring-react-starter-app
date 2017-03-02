package com.codolutions.starterapp.web;

import com.codolutions.starterapp.domain.Comment;
import com.codolutions.starterapp.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    private final CommentService service;

    private final React react;

    private final ObjectMapper mapper;

    @Autowired
    public MainController(CommentService service) {
        this.service = service;
        this.react = new React();
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Map<String, Object> model) throws Exception {
        List<Comment> comments = service.getComments();
        String commentBox = react.renderCommentBox(comments);
        String data = mapper.writeValueAsString(comments);
        model.put("content", commentBox);
        model.put("data", data);
        return "index";
    }
}
