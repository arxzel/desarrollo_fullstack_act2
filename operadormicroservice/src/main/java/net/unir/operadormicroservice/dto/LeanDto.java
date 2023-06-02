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

public class LeanDto {

	@Schema(name = "id", required = false, example = "1", description = "UUID of préstamo into a system DB")
	private Long id;

	@Schema(name = "bookId", required = true, example = "1", description = "UUID of the book in the Buscador microsistem")
	private Long bookId;
	
	@Schema(name = "devolution", required = true, example = "true", description = "Means if it is a devolution operation, if false, it's a rent operation")
	private boolean devolution;

	@Schema(name = "prestamoAt", required = true, example = "", description = "loan date of the book ")
	private Date prestamoAt;

	@Schema(name = "devolutionAt", required = true, example = "", description = "devolution date of the book ")
	private Date devolutionAt;

	@Schema(name = "client", required = true, example = "", description = "client who makes a loan")
	private ClientDto client;
}
