package net.unir.operadormicroservice.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Fabian
 *
 */
@Data
@Schema(description = "This model represent a loan data to a secure api rest comunication")

public class PrestamoDto {

	@Schema(name = "id", required = false, example = "88", description = "UUID of préstamo into a system DB")
	private Long id;

	@Schema(name = "libroId", required = true, example = "13", description = "UUID of the book in the Buscador microsistem")
	private Long libroId;
	
	@Schema(name = "devolution", required = true, example = "true", description = "Means if it is a devolution operation, if false, it's a rent operation")
	private boolean devolution;

	@Schema(name = "prestamoAt", required = true, example = "", description = "loan date of the book ")
	private Date prestamoAt;

	@Schema(name = "devolutionAt", required = true, example = "", description = "devolution date of the book ")
	private Date devolutionAt;

	@Schema(name = "cliente", required = true, example = "", description = "client who makes a loan")
	private ClienteDto cliente;
}
