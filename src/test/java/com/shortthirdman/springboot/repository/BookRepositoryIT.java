package com.shortthirdman.springboot.repository;

import com.shortthirdman.springboot.PostgresDataJpaTest;
import com.shortthirdman.springboot.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@PostgresDataJpaTest
@Sql({"/sql/init.sql"})
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFindById() {
        Optional<Book> bookOptional = bookRepository.findById(1L);

        assertThat(bookOptional).hasValueSatisfying(book -> {
            assertThat(book.getId()).isNotNull();
            assertThat(book.getDescription()).isEqualTo("netus et malesuada");
            assertThat(book.getIsbn()).isEqualTo("X4J 5H8");
            assertThat(book.getPage()).isEqualTo(62);
            assertThat(book.getPrice()).isEqualTo(529);
            assertThat(book.getTitle()).isEqualTo("arcu. Vestibulum ut");
        });
    }

    @Test
    void testFindAll() {
        assertThat(bookRepository.findAll())
                .isNotEmpty()
                .hasSize(10)
                .extracting(Book::getTitle)
                .contains("arcu. Vestibulum ut", "et ipsum cursus", "lorem semper");
    }

    @Test
    void testSave() {
        Book b = Book.builder()
                .title("title")
                .isbn("20351LOPf")
                .page(100)
                .price(250)
                .description("my description")
                .build();

        var book = bookRepository.save(b);
        assertThat(book.getId()).isNotNull();
        assertThat(book.getDescription()).isEqualTo("my description");
        assertThat(book.getIsbn()).isEqualTo("20351LOPf");
        assertThat(book.getPage()).isEqualTo(100);
        assertThat(book.getPrice()).isEqualTo(250);
        assertThat(book.getTitle()).isEqualTo("title");
    }
}
