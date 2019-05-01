package com.cd.rest.controller;

import com.cd.rest.entitymodel.BookTypeEntity;
import com.cd.rest.mapper.BookTypeMapper;
import com.cd.rest.model.BookType;
import com.cd.rest.repository.BookTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booktypes")
public class BookTypeController {

    private BookTypeRepository bookTypeRepository;

    public BookTypeController(BookTypeRepository typerepo) {
        this.bookTypeRepository = typerepo;
    }

    @GetMapping
    public ResponseEntity<List<BookType>> listAll() {
        final List<BookTypeEntity> allTypeEntities = bookTypeRepository.findAll();
        List<BookType> result = new ArrayList<>();

        for(int i = 0; i< allTypeEntities.size(); i++) {
            final BookTypeEntity bookTypeEntity = allTypeEntities.get(i);
            final BookType type = BookTypeMapper.toType(bookTypeEntity);
            result.add(type);
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<BookType> save(@RequestBody BookType type) {
        final BookTypeEntity bookTypeEntity = BookTypeMapper.toTypeEntity(type);
        this.bookTypeRepository.save(bookTypeEntity);
        type.setId(bookTypeEntity.getId());
        return ResponseEntity.ok().body(type);
    }

}
