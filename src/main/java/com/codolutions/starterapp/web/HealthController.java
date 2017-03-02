package com.codolutions.starterapp.web;

import com.codolutions.starterapp.service.CommentService;
import com.codolutions.starterapp.service.DefaultCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/health")
public class HealthController {

    private final CommentService service;

    @Autowired
    public HealthController(DefaultCommentService service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String index() throws Exception {
        if (service != null)
            return "Healthy";
        return "Failure";
    }
}
