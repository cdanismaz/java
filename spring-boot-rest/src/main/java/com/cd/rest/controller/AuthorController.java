package com.cd.rest.controller;

import com.cd.rest.entitymodel.AuthorEntity;
import com.cd.rest.mapper.AuthorMapper;
import com.cd.rest.model.Author;
import com.cd.rest.repository.AuthorRepository;
import com.cd.rest.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Author>> listAll() {
        final List<AuthorEntity> allAuthorEntities = authorRepository.findAll();
        List<Author> allAuthors = new ArrayList<>();

        for(int i = 0; i < allAuthorEntities.size(); i++) {
            final AuthorEntity authorEntity = allAuthorEntities.get(i);
            final Author author = AuthorMapper.toAuthor(authorEntity);
            allAuthors.add(author);
        }

        return ResponseEntity.ok().body(allAuthors);
    }

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody Author author) {
        final AuthorEntity authorEntity = AuthorMapper.toAuthorEntity(author);
        this.authorRepository.save(authorEntity);
        author.setId(authorEntity.getId());
        return ResponseEntity.ok().body(author);
    }
}

