package net.unir.operadormicroservice.service;

import java.util.List;
import java.util.Optional;


import net.unir.operadormicroservice.entity.ClientEntity;

/**
 * @author Fabian
 *
 */
public interface ClientService {

	List<ClientEntity> findAll();

	Optional<ClientEntity> findById(Long id);

	ClientEntity save(ClientEntity entity);

	void deleteById(Long id);
}
