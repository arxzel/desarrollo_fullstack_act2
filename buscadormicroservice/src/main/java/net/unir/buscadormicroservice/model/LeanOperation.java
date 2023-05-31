package net.unir.buscadormicroservice.model;

import lombok.Data;

/**
 * @author Fabian
 *
 */
@Data
public class LeanOperation {
	private Long bookId;
	private LeanOperationType type;
	private boolean success;
	private String message;
	
	
	public enum LeanOperationType{
		RENT, RETURN;
	}
}
