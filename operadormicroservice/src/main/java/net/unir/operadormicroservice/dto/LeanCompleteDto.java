package net.unir.operadormicroservice.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import net.unir.operadormicroservice.model.BookModel;

/**
 * @author Fabian
 *
 */
@Builder
@Data
@Schema(description = "This model represent a loan data to a secure api rest comunication")
public class LeanCompleteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(name = "id", required = false, example = "88", description = "UUID of préstamo into a system DB")
	private Long id;

	@Schema(name = "libroId", required = true, example = "13", description = "UUID of the book in the Buscador microsistem")
	private Long libroId;
	
	@Schema(name = "book", required = true, description = "Book got from buscador-service")
	private transient BookModel book;
	
	@Schema(name = "devolution", required = true, example = "true", description = "Means if it is a devolution operation, if false, it's a rent operation")
	private boolean devolution;

	@Schema(name = "prestamoAt", required = true, example = "", description = "loan date of the book ")
	private Date prestamoAt;

	@Schema(name = "devolutionAt", required = true, example = "", description = "devolution date of the book ")
	private Date devolutionAt;

	@Schema(name = "cliente", required = true, example = "", description = "client who makes a loan")
	private transient ClienteDto cliente;
}
