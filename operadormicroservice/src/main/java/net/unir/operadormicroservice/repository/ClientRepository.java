package net.unir.operadormicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.unir.operadormicroservice.entity.ClientEntity;

/**
 * @author Fabian
 *
 */
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
