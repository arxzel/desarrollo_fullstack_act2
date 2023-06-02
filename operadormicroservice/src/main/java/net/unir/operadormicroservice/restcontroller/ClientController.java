/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.operadormicroservice.restcontroller;

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
import net.unir.operadormicroservice.dto.ClientDto;
import net.unir.operadormicroservice.dto.ClientMapper;
import net.unir.operadormicroservice.entity.ClientEntity;
import net.unir.operadormicroservice.exception.BussinesRuleException;
import net.unir.operadormicroservice.service.ClientService;
import net.unir.operadormicroservice.utils.ClientUtils;

/**
 *
 * @author FABIAN MILLAN
 */
@Tag(name = "Client Operator API", description = "Api of managment clients")
@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clienteService;
	
	@Autowired
	private ClientMapper clienteMapper;
    
	@Operation(summary = "Return all elements bundled into Response",
            description = "this get operation returns all clients into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with clients list"),
        @ApiResponse(responseCode = "204", description = "There are not clients"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping
    public ResponseEntity<List<ClientDto>> list() {
        List<ClientEntity> entityList = clienteService.findAll();
        if (entityList == null || entityList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ClientDto> response = clienteMapper.entityListToModelList(entityList);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return one element bundled into Response",
            description = "This get operation return a client by ID, but you can fill"
                    + " other fields as name, author, etc, and the system will filter "
                    + "the options and return by these other params too")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with client requested by id"),
        @ApiResponse(responseCode = "404", description = "client not found"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> get(@PathVariable Long id) {
        Optional<ClientEntity> entity = clienteService.findById(id);
        if (!entity.isPresent() || entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ClientDto response = clienteMapper.entityToModel(entity.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return persisted element bundled into Response",
            description = "This api operation persist a new client into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Response with client created"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<ClientDto> post(@RequestBody ClientDto input) {
        ClientEntity save = clienteMapper.modelToEntity(input);
        save = clienteService.save(save);
        ClientDto response = clienteMapper.entityToModel(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Return an updated element bundled into Response",
            description = "PUT operation to update a client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Response with client by id"),
        @ApiResponse(responseCode = "404", description = "Client no found"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> put(@PathVariable Long id, @RequestBody ClientDto input) throws BussinesRuleException {
        Optional<ClientEntity> op = clienteService.findById(id);
        if (!op.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ClientEntity ClienteEntity = op.get();
        ClientUtils.mapDtoToentity(input, ClienteEntity);
        ClienteEntity = clienteService.save(ClienteEntity);
        ClientDto response = clienteMapper.entityToModel(ClienteEntity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @Operation(summary = "Delete a client",
            description = "This operation is used to delete a client into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Deletion operation OK"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
