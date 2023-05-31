package net.unir.operadormicroservice.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Fabian
 *
 */
@Data
@Schema(description = "This model represent a client data to a secure api rest comunication")
public class ClienteDto {
	
	@Schema(name = "id", required = false, example = "15", description = "UUID of Client into a system DB")
	private Long id;
	
	@Schema(name = "dni", required = true, example = "123456789", description = "Client's DNI")
	private String dni;

	@Schema(name = "firstName", required = true, example = "Fabián", description = "Client's first name")
	private String firstName;

	@Schema(name = "firstName", required = true, example = "Millán", description = "Client's last name")
	private String lastName;

	@Schema(name = "registeredAt", required = false, example = "", description = "Date when the client was registered, this element will be generated on automatic")
	private Date registeredAt;
}
