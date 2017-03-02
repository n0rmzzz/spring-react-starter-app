package com.codolutions.starterapp.web;

import com.codolutions.starterapp.domain.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

public class ReactTest {
    private final Logger logger = LoggerFactory.getLogger(ReactTest.class);

    @Test
    public void testRenderCommentBox() throws Exception {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("Peter Parker", "This is a comment."));
        comments.add(new Comment("John Doe", "This is *another* comment."));

        React react = new React();
        try {
            String html = react.renderCommentBox(comments);

            assertThat(html, startsWith("<div"));

            Document doc = Jsoup.parse(html);
            assertThat(doc.select("div.comment").size(), is(2));
        } catch (Exception e) {
            logger.warn("Unable to render react component.", e);
            throw e;
        }
    }
}