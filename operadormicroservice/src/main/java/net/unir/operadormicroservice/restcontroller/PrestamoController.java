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
import net.unir.operadormicroservice.dto.LeanCompleteDto;
import net.unir.operadormicroservice.dto.PrestamoDto;
import net.unir.operadormicroservice.dto.PrestamoMapper;
import net.unir.operadormicroservice.entity.ClienteEntity;
import net.unir.operadormicroservice.entity.PrestamoEntity;
import net.unir.operadormicroservice.exception.BussinesRuleException;
import net.unir.operadormicroservice.model.LeanOperation;
import net.unir.operadormicroservice.model.LeanOperation.LeanOperationType;
import net.unir.operadormicroservice.service.ClienteService;
import net.unir.operadormicroservice.service.PrestamoService;
import net.unir.operadormicroservice.utils.PrestamoUtils;

/**
 * @cliente Fabian
 *
 */
@Tag(name = "Lean Operator API", description = "Api of managment leans")
@RestController
@RequestMapping("/api/lean")
public class PrestamoController {
	
	@Autowired
	private PrestamoService prestamoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PrestamoMapper prestamoMapper;
    
	@Operation(summary = "Return all elements bundled into Response",
            description = "this get operation returns all loans into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with loans list"),
        @ApiResponse(responseCode = "204", description = "There are not loans"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping
    public ResponseEntity<List<PrestamoDto>> list() {
        List<PrestamoEntity> entityList = prestamoService.findAll();
        if (entityList == null || entityList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<PrestamoDto> response = prestamoMapper.entityListToModelList(entityList);
        return ResponseEntity.ok(response);
    }
    
	@Operation(summary = "Return all elements bundled into Response",
            description = "this get operation returns all loans into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with loans list"),
        @ApiResponse(responseCode = "204", description = "There are not loans"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/complete")
    public ResponseEntity<List<LeanCompleteDto>> listComplete() {
        List<PrestamoEntity> entityList = prestamoService.findAll();
        if (entityList == null || entityList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<PrestamoDto> prestamoDtos = prestamoMapper.entityListToModelList(entityList);
        List<LeanCompleteDto> response = prestamoService.mapResponseCompleteDto(prestamoDtos);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return one element bundled into Response",
            description = "This get operation return a loan by ID, but you can fill"
                    + " other fields as name, cliente, etc, and the system will filter "
                    + "the options and return by these other params too")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response with loan requested by id"),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDto> get(@PathVariable Long id) {
        Optional<PrestamoEntity> entity = prestamoService.findById(id);
        if (!entity.isPresent() || entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PrestamoDto response = prestamoMapper.entityToModel(entity.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return persisted element bundled into Response",
            description = "This api operation persist a new loan into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Response with loan created"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<?> post(@RequestBody PrestamoDto input) {
        PrestamoEntity save = prestamoMapper.modelToEntity(input);
        LeanOperation operation = LeanOperation.builder()
    			.bookId(save.getLibroId())
    			.type(LeanOperationType.RENT).build();
    	operation = prestamoService.leanOperation(operation);
    	if(operation== null || !operation.isSuccess()) {
    		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(operation.getMessage());
    	}
        save = prestamoService.save(save);
        PrestamoDto response = prestamoMapper.entityToModel(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    

    @Operation(summary = "Return an updated element bundled into Response"
    		+ "\n PLEASE ATENTION: it is not possible to change the book",
            description = "PUT operation to update a loan, EXCEPT Client IF NOT ID related")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Response with loan by Id"),
        @ApiResponse(responseCode = "404", description = "loan no found"),
        @ApiResponse(responseCode = "500", description = "Internal error"),
        @ApiResponse(responseCode = "4988", description = "Cliente information ID NOT FOUND")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody PrestamoDto input) throws BussinesRuleException {
        Optional<PrestamoEntity> op = prestamoService.findById(id);
        if (!op.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        PrestamoEntity prestamoEntity = op.get();
        if (input.getCliente() != null && input.getCliente().getId() != null) {
            Optional<ClienteEntity> clienteop = clienteService.findById(input.getCliente().getId());
            if (!clienteop.isPresent()) {
                throw new BussinesRuleException("4988", "Cliente information ID NOT FOUND", HttpStatus.PRECONDITION_FAILED);
            }
            prestamoEntity.setCliente(clienteop.get());
        }
        
        if(input.isDevolution()) {
        	LeanOperation operation = LeanOperation.builder()
        			.bookId(prestamoEntity.getLibroId())
        			.type(LeanOperationType.RETURN).build();
        	operation = prestamoService.leanOperation(operation);
        	if(!operation.isSuccess()) {
        		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(operation.getMessage());
        	}
        }
        PrestamoUtils.mapDtoToentity(input, prestamoEntity);
        prestamoEntity = prestamoService.save(prestamoEntity);
        PrestamoDto response = prestamoMapper.entityToModel(prestamoEntity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @Operation(summary = "Delete a loan",
            description = "This operation is used to delete a loan into system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Deletion operation OK"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        prestamoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
