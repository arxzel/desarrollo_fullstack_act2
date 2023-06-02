package net.unir.operadormicroservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @author Fabian
 *
 */
@Data
@Entity
@Table(name = "clients", schema = "operador_schema")
public class ClientEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String dni;

	private String firstName;

	private String lastName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registeredAt;
	
	@PrePersist
	private void prePersist(){
		if(this.registeredAt==null) {
			this.registeredAt = new Date();
		}
	}
}
