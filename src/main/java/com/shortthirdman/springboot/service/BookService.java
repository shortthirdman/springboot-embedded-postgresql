package com.shortthirdman.springboot.service;

import com.shortthirdman.springboot.entity.Book;
import com.shortthirdman.springboot.exception.BadRequestException;
import com.shortthirdman.springboot.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    public Book create(Book d) {
        return repository.save(d);
    }

    /**
     * {@inheritDoc}
     */
    public Book update(Book d, Long id) {
        var book = repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Id not found"));
        book.setPage(d.getPage());
        book.setTitle(d.getTitle());
        book.setDescription(d.getDescription());
        book.setIsbn(d.getIsbn());
        book.setPrice(d.getPrice());
        book.setAuthor(d.getAuthor());
        return repository.saveAndFlush(d);
    }

    /**
     * {@inheritDoc}
     */
    public Book getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Id not found"));
    }

    /**
     * {@inheritDoc}
     */
    public List<Book> getAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
