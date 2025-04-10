package com.shortthirdman.springboot.controller;

import com.shortthirdman.springboot.AbstractIntegrationTest;
import com.shortthirdman.springboot.IntegrationTest;
import com.shortthirdman.springboot.entity.Author;
import com.shortthirdman.springboot.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class AuthorControllerIT extends AbstractIntegrationTest {

    @Autowired
    private AuthorRepository repository;

    private Author author;

    @BeforeEach
    public void initTest() {
        repository.deleteAll();
        author = Author.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .build();
    }

    @Test
    @Transactional
    void createAuthor() throws Exception {
        int databaseSizeBeforeCreate = repository.findAll().size();

        mockMvc
                .perform(post(MessageFormat.format("{0}/author", BASE_CONTROLLER_ENDPOINT)).contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(author)))
                .andExpect(status().isCreated());

        // Validate the Author in the database
        assertPersistedAuthors(authors -> {
            assertThat(authors).hasSize(databaseSizeBeforeCreate + 1);
            Author testAuthor = authors.getLast();
            assertThat(testAuthor.getLastName()).isEqualTo(author.getLastName());
            assertThat(testAuthor.getFirstName()).isEqualTo(author.getFirstName());
        });
    }

    private void assertPersistedAuthors(Consumer<List<Author>> userAssertion) {
        userAssertion.accept(repository.findAll());
    }
}
