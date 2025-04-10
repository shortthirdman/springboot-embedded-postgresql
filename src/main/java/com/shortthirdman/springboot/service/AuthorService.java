package com.shortthirdman.springboot.service;

import com.shortthirdman.springboot.entity.Author;
import com.shortthirdman.springboot.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    public Author create(Author d) {
        return repository.save(d);
    }

    /**
     * {@inheritDoc}
     */
    public Author update(Author d) {
        return repository.saveAndFlush(d);
    }

    /**
     * {@inheritDoc}
     */
    public Author getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    public List<Author> getAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
