package net.unir.operadormicroservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fabian
 *
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeanOperation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long bookId;
	private LeanOperationType type;
	private boolean success;
	private String message;
	
	
	public enum LeanOperationType {
		RENT, RETURN;
	}
}
