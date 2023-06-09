package net.unir.operadormicroservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author FABIAN MILLAN
 */
@Data
@Entity
@Table(name = "leans", schema = "operador_schema")
public class LeanEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long bookId;
	
	private boolean devolution;
		
	private Date prestamoAt;
	
	private Date devolutionAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private ClientEntity client;
	
	@PrePersist
	private void prePersist() {
		if(prestamoAt == null) {
			prestamoAt = new Date();
		}
		devolutionProcess();
	}
	
	@PreUpdate
	private void preUpdate() {
		devolutionProcess();
	}
	
	private void devolutionProcess() {
		if(this.isDevolution() && devolutionAt == null) {
			this.devolutionAt = new Date();
		}
	}

}
