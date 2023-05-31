package net.unir.operadormicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.unir.operadormicroservice.entity.ClienteEntity;

/**
 * @author Fabian
 *
 */
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
