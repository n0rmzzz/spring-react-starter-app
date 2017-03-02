package com.codolutions.starterapp.model;

import com.codolutions.starterapp.domain.Comment;
import com.codolutions.starterapp.service.CommentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith (SpringRunner.class)
@DataJpaTest
public class CommentRepositoryIntegrationTests {
    @Autowired
    private CommentRepository customerRepository;

    @Test
    public void testFindByLastName() {
        Page<Comment> customers = customerRepository.findAll(new PageRequest(0, 10));
        assertThat(customers.getTotalElements()).isEqualTo(0L);

        customerRepository.save(new Comment("Andre Gide",
                                            "It is better to be hated for what you are than to be loved for what you are not."));
        customerRepository.save(new Comment("Robert R. Coveyou",
                                            "The generation of random numbers is too important to be left to chance."));
        customerRepository.save(new Comment("Benjamin H. Brewster",
                                            "A lawyer starts life giving $500 worth of law for $5 and ends giving $5 worth for $500."));
        customerRepository.save(new Comment("Theodore Roethke",
                                            "A mind too active is no mind at all."));
        customerRepository.save(new Comment("Evan Esar",
                                            "The girl with a future avoids a man with a past."));

        customers = customerRepository.findAll(new PageRequest(0, 10));
        assertThat(customers.getTotalElements()).isEqualTo(5L);

        Comment customer = customerRepository.findByAuthor("Evan Esar", new PageRequest(0, 10)).getContent().get(0);
        assertThat(customer.getText()).isEqualTo("The girl with a future avoids a man with a past.");
        assertThat(customer.getAuthor()).isEqualTo("Evan Esar");
        assertThat(customers.getTotalElements()).isEqualTo(5L);
    }
}
