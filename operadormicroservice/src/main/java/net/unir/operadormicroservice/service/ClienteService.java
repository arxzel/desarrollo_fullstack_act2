package net.unir.operadormicroservice.service;

import java.util.List;
import java.util.Optional;


import net.unir.operadormicroservice.entity.ClienteEntity;

/**
 * @author Fabian
 *
 */
public interface ClienteService {

	List<ClienteEntity> findAll();

	Optional<ClienteEntity> findById(Long id);

	ClienteEntity save(ClienteEntity entity);

	void deleteById(Long id);
}
