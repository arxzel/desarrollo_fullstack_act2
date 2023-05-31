/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.unir.buscadormicroservice.dto.AuthorDto;
import net.unir.buscadormicroservice.dto.AuthorMapper;
import net.unir.buscadormicroservice.entity.AuthorEntity;
import net.unir.buscadormicroservice.exception.BussinesRuleException;
import net.unir.buscadormicroservice.service.AuthorService;
import net.unir.buscadormicroservice.utils.AuthorUtils;

/**
 *
 * @author FABIAN MILLAN
 */
@Tag(name = "author buscador API", description = "Api of managment all authors")
@RestController
@RequestMapping("/api/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private AuthorMapper authorMapper;
    
    @GetMapping
    public ResponseEntity<List<AuthorDto>> list(){
        List<AuthorEntity> entityList = authorService.findAll();
        if (entityList == null || entityList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AuthorDto> response = authorMapper.entityListToModelList(entityList);
        return ResponseEntity.ok(response);        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@PathVariable Long id) {
        Optional<AuthorEntity> entity = authorService.findById(id);
        if (!entity.isPresent() || entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AuthorDto response = authorMapper.entityToModel(entity.get());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> post(@RequestBody AuthorDto input) {
        AuthorEntity save = authorMapper.modelToEntity(input);
        save = authorService.save(save);
        AuthorDto response = authorMapper.entityToModel(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> put(@PathVariable Long id, @RequestBody AuthorDto input) throws BussinesRuleException {
        Optional<AuthorEntity> op = authorService.findById(id);
        if (!op.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AuthorEntity authorEntity = op.get();
        AuthorUtils.mapDtoToentity(input, authorEntity);
        authorEntity = authorService.save(authorEntity);
        AuthorDto response = authorMapper.entityToModel(authorEntity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
