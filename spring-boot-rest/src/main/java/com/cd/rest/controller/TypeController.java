package com.cd.rest.controller;

import com.cd.rest.entitymodel.TypeEntity;
import com.cd.rest.mapper.TypeMapper;
import com.cd.rest.model.Types;
import com.cd.rest.repository.TypesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    private TypesRepository typesRepository;

    public TypeController(TypesRepository typerepo) {
        this.typesRepository = typerepo;
    }

    @GetMapping
    //@RequestMapping(value="/list", method = RequestMethod.GET)
    public List<Types> typeslist() {
        final List<TypeEntity> alltypentities = typesRepository.findAll();
        List<Types> result = new ArrayList<>();

        for(int i = 0; i< alltypentities.size(); i++) {
            final TypeEntity typeEntity = alltypentities.get(i);
            final Types type = TypeMapper.toType(typeEntity);
            result.add(type);
        }
        return result;
    }

    @PostMapping
    public ResponseEntity saveTypeAPI(@RequestBody Types type, HttpServletRequest request) {
//        final String header1 = request.getHeader("cansu");
//        if(header1 == null)
//            return ResponseEntity.badRequest().body(false);
        final TypeEntity typeEntity = TypeMapper.toTypeEntity(type);
        this.typesRepository.save(typeEntity);
        return ResponseEntity.ok(true);
    }

}
