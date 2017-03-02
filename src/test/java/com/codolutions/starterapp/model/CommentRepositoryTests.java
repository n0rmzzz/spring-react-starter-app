package com.codolutions.starterapp.model;

import java.util.List;

import com.codolutions.starterapp.domain.Comment;
import com.codolutions.starterapp.service.CommentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith (SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CommentRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testFindByLastName() {
        Comment comment = new Comment("name", "text is here");
        entityManager.persist(comment);

        List<Comment> foundByAuthor =
                commentRepository.findByAuthor(comment.getAuthor(), new PageRequest(0, 10)).getContent();

        assertThat(foundByAuthor).extracting(Comment::getText).containsOnly(comment.getText());
    }
}
