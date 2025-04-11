package com.shortthirdman.springboot.repository;

import com.shortthirdman.springboot.PostgresDataJpaTest;
import com.shortthirdman.springboot.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@PostgresDataJpaTest
@Sql({"/sql/init.sql"})
public class AuthorRepositoryIT {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testFindById() {
        Optional<Author> authorOptional = authorRepository.findById(1L);

        assertThat(authorOptional).hasValueSatisfying(author -> {
            assertThat(author.getId()).isNotNull();
            assertThat(author.getFirstName()).isEqualTo("Bree");
            assertThat(author.getLastName()).isEqualTo("Nasim");
        });
    }
}
