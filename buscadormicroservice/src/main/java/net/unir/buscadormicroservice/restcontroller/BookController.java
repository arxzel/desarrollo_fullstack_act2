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
import org.springframework.web.bind.annotation.PatchMapping;
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
import net.unir.buscadormicroservice.dto.BookDto;
import net.unir.buscadormicroservice.dto.BookMapper;
import net.unir.buscadormicroservice.entity.AuthorEntity;
import net.unir.buscadormicroservice.entity.BookEntity;
import net.unir.buscadormicroservice.exception.BussinesRuleException;
import net.unir.buscadormicroservice.model.LeanOperation;
import net.unir.buscadormicroservice.service.AuthorService;
import net.unir.buscadormicroservice.service.BookService;
import net.unir.buscadormicroservice.utils.BookUtils;

/**
 *
 * @author FABIAN MILLAN
 */
@Tag(name = "Book buscador API", description = "Api of managment all books")
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookMapper bookMapper;

    @Operation(summary = "Return all elements bundled into Response",
            description = "this get operation returns all books into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with books list"),
        @ApiResponse(responseCode = "204", description = "There are not books"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping
    public ResponseEntity<List<BookDto>> list() {
        List<BookEntity> entityList = bookService.findAll();
        if (entityList == null || entityList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<BookDto> response = bookMapper.entityListToModelList(entityList);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return one element bundled into Response",
            description = "This get operation return a book by ID, but you can fill"
                    + " other fields as name, author, etc, and the system will filter "
                    + "the options and return by these other params too")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with book requested by id"),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable Long id) {
        Optional<BookEntity> entity = bookService.findById(id);
        if (!entity.isPresent() || entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BookDto response = bookMapper.entityToModel(entity.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return persisted element bundled into Response",
            description = "This api operation persist a new book into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Response with book created"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<BookDto> post(@RequestBody BookDto input) {
        BookEntity save = bookMapper.modelToEntity(input);
        save = bookService.save(save);
        BookDto response = bookMapper.entityToModel(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Return an updated element bundled into Response",
            description = "PUT operation to update a book, EXCEPT AUTHOR IF NOT ID related")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Response with books list"),
        @ApiResponse(responseCode = "204", description = "There are not books"),
        @ApiResponse(responseCode = "500", description = "Internal error"),
        @ApiResponse(responseCode = "4988", description = "Author information ID NOT FOUND")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> put(@PathVariable Long id, @RequestBody BookDto input) throws BussinesRuleException {
        Optional<BookEntity> op = bookService.findById(id);
        if (!op.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        BookEntity bookEntity = op.get();
        if (input.getAuthor() != null && input.getAuthor().getId() != null) {
            Optional<AuthorEntity> authorop = authorService.findById(input.getAuthor().getId());
            if (!authorop.isPresent()) {
                throw new BussinesRuleException("4988", "Author information ID NOT FOUND", HttpStatus.PRECONDITION_FAILED);
            }
            bookEntity.setAuthor(authorop.get());
        }
        BookUtils.mapDtoToentity(input, bookEntity);
        bookEntity = bookService.save(bookEntity);
        BookDto response = bookMapper.entityToModel(bookEntity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @Operation(summary = "Delete a book",
            description = "This operation is used to delete a book into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Deletion operation OK"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<LeanOperation> loanOperation(@PathVariable Long id, @RequestBody LeanOperation operation){
    	LeanOperation response = bookService.leanOperationBook(operation);
    	if(response == null) {
    		return ResponseEntity.notFound().build();
    	}
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
