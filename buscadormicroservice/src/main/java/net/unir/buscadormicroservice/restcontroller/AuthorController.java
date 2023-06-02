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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    
    
    @Operation(summary = "Return all elements bundled into Response",
            description = "this get operation returns all authors into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with authors list"),
        @ApiResponse(responseCode = "204", description = "There are not authors"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping
    public ResponseEntity<List<AuthorDto>> list(){
        List<AuthorEntity> entityList = authorService.findAll();
        if (entityList == null || entityList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AuthorDto> response = authorMapper.entityListToModelList(entityList);
        return ResponseEntity.ok(response);        
    }
    
    
    @Operation(summary = "Return one element bundled into Response",
            description = "This get operation return a author by ID, but you can fill"
                    + " other fields as name, author, etc, and the system will filter "
                    + "the options and return by these other params too")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with author requested by id"),
        @ApiResponse(responseCode = "404", description = "Author not found"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@PathVariable Long id) {
        Optional<AuthorEntity> entity = authorService.findById(id);
        if (!entity.isPresent() || entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AuthorDto response = authorMapper.entityToModel(entity.get());
        return ResponseEntity.ok(response);
    }

    
    @Operation(summary = "Return persisted element bundled into Response",
            description = "This api operation persist a new author into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Response with author created"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<AuthorDto> post(@RequestBody AuthorDto input) {
        AuthorEntity save = authorMapper.modelToEntity(input);
        save = authorService.save(save);
        AuthorDto response = authorMapper.entityToModel(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    @Operation(summary = "Return an updated element bundled into Response",
            description = "PUT operation to update a author, EXCEPT AUTHOR IF NOT ID related")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Response with authors list"),
        @ApiResponse(responseCode = "204", description = "There are not authors"),
        @ApiResponse(responseCode = "500", description = "Internal error"),
        @ApiResponse(responseCode = "4988", description = "Author information ID NOT FOUND")
    })
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

    @Operation(summary = "Delete a author",
            description = "This operation is used to delete a author into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Deletion operation OK"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
